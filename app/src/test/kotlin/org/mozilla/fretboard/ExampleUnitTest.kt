package org.mozilla.fretboard

import org.junit.Test

import org.junit.Assert.*
import java.net.URL

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FretBoardUnitTest {
    private val TAG = "Test"

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun configLoader_isCorrect() {
        val configLoader: ConfigLoader = HttpURLConnectionConfigLoader()
        val url = URL("https://firefox.settings.services.mozilla.com/v1/buckets/fennec/collections/experiments/records")

        val result: String? = configLoader.loadConfig(url);
        println("Result: $TAG: $result")
    }
}


