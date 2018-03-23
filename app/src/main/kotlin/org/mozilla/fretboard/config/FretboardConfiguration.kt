package org.mozilla.fretboard

import android.content.Context


class FretboardConfiguration {
    private val DEFAULT_INITAL_BACKOFF_FOR_UPLOAD: Long = 30000

    private val initialBackoffForUpload: Long = DEFAULT_INITAL_BACKOFF_FOR_UPLOAD
    private val context: Context? = null

    fun getContext(): Context? {
        return context
    }

    fun getInitialBackoffForUpload(): Long {
        return initialBackoffForUpload
    }


}