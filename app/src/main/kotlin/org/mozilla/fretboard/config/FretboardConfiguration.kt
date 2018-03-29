package org.mozilla.fretboard.config

import android.content.Context


class FretboardConfiguration {
    //TODO fix this staff with constants (just for fast testing)
    private val DEFAULT_INITAL_BACKOFF_FOR_UPLOAD: Long = 30000
    private val DEFAULT_USER_AGENT: String = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:59.0) Gecko/20100101 Firefox/59.0"
    private val DEFAULT_PASSWORD = "my-secret"
    private val DEFAULT_USER_NAME = "token"
    private val DEFAULT_SERVER_ENDPOINT = "https://kinto.dev.mozaws.net"

    private var context: Context? = null
    private val initialBackoffForUpload: Long = DEFAULT_INITAL_BACKOFF_FOR_UPLOAD
    private val userAgent: String? = DEFAULT_USER_AGENT
    private val name: String = DEFAULT_USER_NAME
    private val password: String = DEFAULT_PASSWORD
    private val serverEndpoint : String = DEFAULT_SERVER_ENDPOINT

    fun getServerEndpoint() : String {
        return serverEndpoint
    }

    fun getUserName(): String {
        return name
    }

    fun getPassword(): String {
        return password
    }

    fun getUserAgent(): String? {
        return userAgent
    }

    fun getContext(): Context? {
        return context
    }

    fun setContext(context: Context) {
        this.context = context
    }

    fun getInitialBackoffForUpload(): Long {
        return initialBackoffForUpload
    }


}