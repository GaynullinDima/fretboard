package org.mozilla.fretboard.helpers

import android.content.Context
import org.mozilla.fretboard.extensions.getConfigSharedPrefs


class FretboardConfiguration(context: Context) {
    private val prefs = context.getConfigSharedPrefs()

    val context = context

    // Parameters for experiments uploader
    var serverPath: String
        get() = prefs.getString(SERVER_PATH, DEFAULT_SERVER_PATH)
        set(serverPath) = prefs.edit().putString(SERVER_PATH, serverPath).apply()

    var serverEndpoint: String
        get() = prefs.getString(SERVER_ENDPOINT, DEFAULT_SERVER_ENDPOINT)
        set(serverEndpoint) = prefs.edit().putString(SERVER_ENDPOINT, serverEndpoint).apply()

    var connectTimeout: Int
        get() = prefs.getInt(CONNECT_TIMEOUT, DEFAULT_CONNECT_TIMEOUT)
        set(connectTimeout) = prefs.edit().putInt(CONNECT_TIMEOUT, connectTimeout).apply()

    var readTimeout: Int
        get() = prefs.getInt(READ_TIMEOUT, DEFAULT_READ_TIMEOUT)
        set(readTimeout) = prefs.edit().putInt(READ_TIMEOUT, readTimeout).apply()

    var lastTimeStamp: String
        get() = prefs.getString(TIMESTAMP, DEFAULT_TIMESTAMP)
        set(lastTimeStamp) = prefs.edit().putString(TIMESTAMP, lastTimeStamp).apply()

    var userAgent: String
        get() = prefs.getString(USER_AGENT, DEFAULT_USER_AGENT)
        set(userAgent) = prefs.edit().putString(USER_AGENT, userAgent).apply()

    var name: String
        get() = prefs.getString(USER_NAME, DEFAULT_USER_NAME)
        set(name) = prefs.edit().putString(USER_NAME, name).apply()

    var password: String
        get() = prefs.getString(PASSWORD, DEFAULT_PASSWORD)
        set(password) = prefs.edit().putString(PASSWORD, password).apply()

    // Parameters for Fretboard Scheduler
    var initialBackoffForUpload: Long
        get() = prefs.getLong(INITAL_BACKOFF_FOR_UPLOAD, DEFAULT_INITAL_BACKOFF_FOR_UPLOAD)
        set(initialBackoffForUpload) = prefs.edit().putLong(INITAL_BACKOFF_FOR_UPLOAD,
                initialBackoffForUpload).apply()

    var periodicInterval: Long
        get() = prefs.getLong(PERIODIC_INTERVAL, DEFAULT_PERIODIC_INTERVAL)
        set(periodicInterval) = prefs.edit().putLong(PERIODIC_INTERVAL, periodicInterval).apply()
}