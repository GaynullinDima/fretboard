package org.mozilla.fretboard.helpers

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class FretboardConfigTest {
    private val TAG = "FretboardConfigTest"

    @Test
    fun configSharedPreferencesDefaultsWorkCorrectly() {
        val context = RuntimeEnvironment.application
        val config = FretboardConfiguration(context)

        assertEquals(config.connectTimeout, DEFAULT_CONNECT_TIMEOUT)
        assertEquals(config.serverEndpoint, DEFAULT_SERVER_ENDPOINT)
        assertEquals(config.serverPath, DEFAULT_SERVER_PATH)
        assertEquals(config.lastTimeStamp, DEFAULT_TIMESTAMP)
        assertEquals(config.password, DEFAULT_PASSWORD)
        assertEquals(config.userAgent, DEFAULT_USER_AGENT)
        assertEquals(config.readTimeout, DEFAULT_READ_TIMEOUT)
        assertEquals(config.initialBackoffForUpload, DEFAULT_INITAL_BACKOFF_FOR_UPLOAD)
        assertEquals(config.name, DEFAULT_USER_NAME)
    }

    @Test
    fun configSharedPreferencesSettersWorkCorrectly() {
        val context = RuntimeEnvironment.application
        val config = FretboardConfiguration(context)

        config.connectTimeout = 11
        assertEquals(config.connectTimeout, 11)
        config.serverEndpoint = "test1"
        assertEquals(config.serverEndpoint, "test1")
        config.serverPath = "test2"
        assertEquals(config.serverPath, "test2")
        config.lastTimeStamp = "test3"
        assertEquals(config.lastTimeStamp, "test3")
        config.password = "test4"
        assertEquals(config.password, "test4")
        config.userAgent = "test5"
        assertEquals(config.userAgent, "test5")
        config.readTimeout = 42
        assertEquals(config.readTimeout, 42)
        config.initialBackoffForUpload = 43
        assertEquals(config.initialBackoffForUpload, 43)
        config.name = "test6"
        assertEquals(config.name, "test6")
    }
}