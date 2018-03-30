package org.mozilla.fretboard.net

import org.junit.Test
import org.junit.runner.RunWith
import org.mozilla.fretboard.config.FretboardConfiguration
import org.robolectric.RobolectricTestRunner

// TODO rewrite tests
@RunWith(RobolectricTestRunner::class)
class FretboardConfigLoaderTest {
    private val TAG = "FretboardConfigLoaderTest"


    @Test
    fun configLoader_isCorrect() {
        val configLoader = HttpURLConnectionFretboardClient()
        val config = FretboardConfiguration()
        val path = "/v1/buckets/default/collections/tasks/records"
        val result: String? = configLoader.downloadExperiment(config, path)
        println("Result: $TAG: $result")
    }
}

