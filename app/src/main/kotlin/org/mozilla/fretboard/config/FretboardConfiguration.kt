package org.mozilla.fretboard.config

import android.content.Context


class FretboardConfiguration {
    //TODO fix this staff with constants (just for fast testing)
    private val DEFAULT_INITAL_BACKOFF_FOR_UPLOAD: Long = 30000
    private val DEFAULT_USER_AGENT: String = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:59.0) Gecko/20100101 Firefox/59.0"
    private val DEFAULT_PASSWORD = "my-secret"
    private val DEFAULT_USER_NAME = "token"
    private val DEFAULT_SERVER_ENDPOINT = "https://kinto.dev.mozaws.net"
    private val DEFAULT_CONNECT_TIMEOUT = 10000
    private val DEFAULT_READ_TIMEOUT = 30000
    private val DEFAULT_TIME_STAMP = "0"


    var context: Context? = null
    var serverEndpoint : String = DEFAULT_SERVER_ENDPOINT
    var connectTimeout: Int = DEFAULT_CONNECT_TIMEOUT
    var readTimeout: Int = DEFAULT_READ_TIMEOUT
    var lastTimeStamp = DEFAULT_TIME_STAMP


    val initialBackoffForUpload: Long = DEFAULT_INITAL_BACKOFF_FOR_UPLOAD
    val userAgent: String? = DEFAULT_USER_AGENT
    val name: String = DEFAULT_USER_NAME
    val password: String = DEFAULT_PASSWORD


}