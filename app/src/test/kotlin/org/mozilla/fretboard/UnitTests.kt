package org.mozilla.fretboard

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.AsyncTask
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mozilla.fretboard.config.FretboardConfiguration
import org.mozilla.fretboard.net.ConfigLoader
import org.mozilla.fretboard.net.HttpURLConnectionConfigLoader
import org.mozilla.fretboard.sheduler.FretboardJobService
import org.mozilla.fretboard.storage.ConfigStorage
import org.mozilla.fretboard.storage.SharedPreferenceConfigStorage
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment


import java.net.URL
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.Mockito.*
import org.robolectric.Robolectric




/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


// TODO rewrite tests
@RunWith(RobolectricTestRunner::class)
class FretboardConfigLoaderTest {
    private val TAG = "FretboardConfigLoaderTest"


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
class FretboardConfigStorageTest {
    private val TAG = "FretboardConfigStorageTest"


    @Test
    fun testOverMultipleSession() {
        val context = RuntimeEnvironment.application
        val config = FretboardConfiguration()
        config.setContext(context)
        val storage: ConfigStorage = SharedPreferenceConfigStorage()

        for (i in 1..10) {
            storage.setExperimentJson(config, "Test : $i")
        }

        println(storage.getExperimentJson(config))
        assertEquals(storage.getExperimentJson(config), "Test : 10")

    }

    @Test
    fun testResettingExperimentManually() {
        val context = RuntimeEnvironment.application
        val config = FretboardConfiguration()
        config.setContext(context)
        val storage: ConfigStorage = SharedPreferenceConfigStorage()

        storage.clearOverrideValue(config, "Test")
        storage.setOverrideValue(config, "Test", true)

        // ugly hack
        assertEquals(storage.getOverrideValue(config, "Test"), true)
        println(storage.getOverrideValue(config, "Test"))
        storage.setOverrideValue(config, "Test", false)
        assertEquals(storage.getOverrideValue(config, "Test"), false)
        println(storage.getOverrideValue(config, "Test"))

    }
}

@RunWith(RobolectricTestRunner::class)
class FretboardJobServiceTest {
    private val TAG = "FretboardConfigLoaderTest"

    @Test
    fun testServiceisWorking() {
        val parameters = mock(JobParameters::class.java)
        val task = mock(AsyncTask::class.java)
        val path =  "/v1/buckets/default/collections/tasks/records"
        val context = RuntimeEnvironment.application
        val service = FretboardJobService(context)
        /*val service = spy(Robolectric.buildService(FretboardJobService::class.java!!)
                .create()
                .get())

        doNothing().`when`(service).jobFinished(any(JobParameters::class.java), anyBoolean())
        */ //TODO refactor
        service.loadExperimentsInBackground(path, task, parameters)
    }

}





