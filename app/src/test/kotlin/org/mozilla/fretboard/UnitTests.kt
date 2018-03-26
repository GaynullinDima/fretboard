package org.mozilla.fretboard

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mozilla.fretboard.config.FretboardConfiguration
import org.mozilla.fretboard.net.ConfigLoader
import org.mozilla.fretboard.net.HttpURLConnectionConfigLoader
import org.mozilla.fretboard.storage.ConfigStorage
import org.mozilla.fretboard.storage.SharedPreferenceConfigStorage
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment


import java.net.URL


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


// TODO rewrite tests
@RunWith(RobolectricTestRunner::class)
class ConfigLoaderUnitTest {
    private val TAG = "ConfigLoaderUnitTest"


    @Test
    fun configLoader_isCorrect() {
        val configLoader: ConfigLoader = HttpURLConnectionConfigLoader()
        val config = FretboardConfiguration()
        val path = "/v1/buckets/default/collections/tasks/records"
        val result: String? = configLoader.loadConfig(config, path)
        println("Result: $TAG: $result")
    }
}

@RunWith(RobolectricTestRunner::class)
class ConfigStorageUnitTest {
    private val TAG = "ConfigStorageUnitTest"


    @Test
    fun testOverMultipleSession() {
        val context = RuntimeEnvironment.application
        val storage: ConfigStorage = SharedPreferenceConfigStorage()

        for (i in 1..10) {
            storage.setConfigJson(context, "Test : $i")
        }

        println(storage.getConfigJson(context))
        assertEquals(storage.getConfigJson(context), "Test : 10")

    }

    @Test
    fun testResettingExperimentManually() {
        val context = RuntimeEnvironment.application
        val storage: ConfigStorage = SharedPreferenceConfigStorage()

        storage.clearOverrideValue(context, "Test")
        storage.setOverrideValue(context, "Test", true)

        // ugly hack
        assertEquals(storage.getOverrideValue(context, "Test"), true)
        println(storage.getOverrideValue(context, "Test"))
        storage.setOverrideValue(context, "Test", false)
        assertEquals(storage.getOverrideValue(context, "Test"), false)
        println(storage.getOverrideValue(context, "Test"))

    }
}




