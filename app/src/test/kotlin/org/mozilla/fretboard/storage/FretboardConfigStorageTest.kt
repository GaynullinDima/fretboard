package org.mozilla.fretboard.storage

import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mozilla.fretboard.config.FretboardConfiguration
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

// TODO rewrite tests
@RunWith(RobolectricTestRunner::class)
class FretboardConfigStorageTest {
    private val TAG = "FretboardConfigStorageTest"


    @Test
    fun testOverMultipleSession() {
        val context = RuntimeEnvironment.application
        val config = FretboardConfiguration()
        config.context = context
        val storage: ConfigStorage = SharedPreferenceConfigStorage()

        for (i in 1..10) {
            storage.setExperimentJson(config, "Test : $i")
        }

        println(storage.getExperimentJson(config))
        Assert.assertEquals(storage.getExperimentJson(config), "Test : 10")

    }

    @Test
    fun testResettingExperimentManually() {
        val context = RuntimeEnvironment.application
        val config = FretboardConfiguration()
        config.context = context
        val storage: ConfigStorage = SharedPreferenceConfigStorage()

        storage.clearOverrideValue(config, "Test")
        storage.setOverrideValue(config, "Test", true)

        // TODO refactor this ugly hack
        Assert.assertEquals(storage.getOverrideValue(config, "Test"), true)
        println(storage.getOverrideValue(config, "Test"))
        storage.setOverrideValue(config, "Test", false)
        Assert.assertEquals(storage.getOverrideValue(config, "Test"), false)
        println(storage.getOverrideValue(config, "Test"))

    }
}




