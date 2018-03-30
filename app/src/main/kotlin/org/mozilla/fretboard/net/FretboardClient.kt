package org.mozilla.fretboard.net


import android.util.Base64
import android.util.Log
import org.mozilla.fretboard.config.FretboardConfiguration
import org.mozilla.fretboard.utils.readStream
import java.io.BufferedInputStream
import java.io.IOException

import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


interface FretboardClient {
    fun downloadExperiment(config: FretboardConfiguration, path: String): String?
}


class HttpURLConnectionFretboardClient : FretboardClient {
    private val TAG = "HttpURLFretboardClient"

    override fun downloadExperiment(config: FretboardConfiguration, path: String): String? {
        var connection: HttpURLConnection? = null


        try {
            // Constructing connection with server
            val encodedUserPass = encodeUserPassForAuthorization(config.name, config.password)
            connection = openConnectionConnection(config.serverEndpoint, path, config.lastTimeStamp)
            connection.connectTimeout = config.connectTimeout
            connection.readTimeout = config.readTimeout
            connection.requestMethod = "GET"
            connection.useCaches = false
            connection.setRequestProperty("User-Agent", config.userAgent)
            connection.setRequestProperty("Authorization", "Basic $encodedUserPass")


            val (result, responseCode) = download(connection)
            Log.d(TAG, "Experiment download: $responseCode")
            when (responseCode) {
                in 200..299 -> {
                    // Update last update time stamp
                    val responseHeadersMap = connection.headerFields
                    if (responseHeadersMap.containsKey("ETag")) {
                        val res = responseHeadersMap["ETag"]
                        config.lastTimeStamp = res!![0]
                    }
                }
                in 400..499 ->
                    Log.e(TAG, "Server returned client error code: $responseCode")
                else ->
                    Log.w(TAG, "Server returned response code: $responseCode")
            }

            return result
        } catch (e: MalformedURLException) {
            Log.e(TAG, "Could not download experiment due to malformed URL", e)
        } catch (e: IOException) {
            Log.w(TAG, "IOException while downloading experiment", e)
        } finally {
            if (connection != null) connection.disconnect()
        }
        return null
    }


    private fun openConnectionConnection(endpoint: String, path: String,
                                         lastTimeStamp: String): HttpURLConnection {
        val url = URL("$endpoint$path?_since=$lastTimeStamp")
        return url.openConnection() as HttpURLConnection
    }

    private fun encodeUserPassForAuthorization(user: String, pass: String): String {
        val userPass = "$user:$pass"
        return Base64.encodeToString(userPass.toByteArray(), Base64.NO_WRAP)
    }

    private fun download(connection: HttpURLConnection): Pair<String?, Int> {
        val stream = BufferedInputStream(connection.inputStream)
        var data: String? = null
        stream.use {
            data = readStream(inputStream = stream)
        }
        val responseCode = connection.responseCode
        return Pair(data, responseCode)
    }


}
