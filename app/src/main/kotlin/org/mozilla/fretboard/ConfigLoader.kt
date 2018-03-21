package org.mozilla.fretboard


import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

interface ConfigLoader {
    fun loadConfig(url: URL): String?
}

class HttpURLConnectionConfigLoader : ConfigLoader {
    override fun loadConfig(url: URL): String? {
        val connection = url.openConnection() as HttpURLConnection

        try {
            connection.setRequestProperty("User-Agent", System.getProperty("http.agent"))
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
