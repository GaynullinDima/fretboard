package org.mozilla.fretboard.storage

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

// TODO rewrite tests after refactoring
@RunWith(RobolectricTestRunner::class)
class FretboardConfigStorageTest {
    private val TAG = "FretboardConfigStorageTest"


    @Test
    fun testOverMultipleSession() {
        val context = RuntimeEnvironment.application
        val storage = SharedPreferenceFretboardExperimentsStorage(context)

        for (i in 1..10) {
            storage.setExperimentJson("Test : $i")
        }

        println(storage.getExperimentJson())
        assertEquals(storage.getExperimentJson(), "Test : 10")

    }

    @Test
    fun testResettingExperimentManually() {
    }
}




