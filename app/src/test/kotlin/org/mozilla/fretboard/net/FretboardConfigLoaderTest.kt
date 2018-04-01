package org.mozilla.fretboard.net

import org.junit.Test
import org.junit.runner.RunWith
import org.mozilla.fretboard.extensions.fretboardConfig
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

// TODO rewrite tests
@RunWith(RobolectricTestRunner::class)
class FretboardConfigLoaderTest {
    private val TAG = "FretboardConfigLoaderTest"


    @Test
    fun configLoader_isCorrect() {
        val configLoader = HttpURLConnectionFretboardClient()
        val context = RuntimeEnvironment.application
        val config = context.fretboardConfig
        val result: String? = configLoader.updateExperimentConfig(config)
        println("Result: $TAG: $result")
    }
}

