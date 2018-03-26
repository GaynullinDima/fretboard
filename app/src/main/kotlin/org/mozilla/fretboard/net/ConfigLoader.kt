package org.mozilla.fretboard.net


import android.util.Base64
import org.mozilla.fretboard.config.FretboardConfiguration
import org.mozilla.fretboard.utils.readStream
import java.io.BufferedInputStream
import java.io.DataOutputStream
import java.net.Authenticator
import java.net.HttpURLConnection
import java.net.PasswordAuthentication
import java.net.URL


interface ConfigLoader {
    fun loadConfig(config: FretboardConfiguration, path: String?): String?
}


class HttpURLConnectionConfigLoader : ConfigLoader {
    override fun loadConfig(config: FretboardConfiguration, path: String?): String? {


        val user_pass = "${config.getUserName()}:${config.getPassword()}"

        val encoded = Base64.encodeToString(user_pass.toByteArray(), Base64.NO_WRAP)
        val url = URL("${config.getServerEndpoint()}$path")

        val connection = url.openConnection() as HttpURLConnection
        try {

            connection.setRequestProperty("User-Agent", config.getUserAgent())
            connection.setRequestProperty("Authorization", "Basic $encoded")
            connection.requestMethod = "GET"
            connection.useCaches = false


            val stream = BufferedInputStream(connection.inputStream)
            val data: String = readStream(inputStream = stream)
            return data
        } catch (e: Exception) {

            e.printStackTrace()
        } finally {
            if (connection != null) connection.disconnect()
        }
        return null
    }

}
