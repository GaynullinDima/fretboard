package org.mozilla.fretboard.scheduler

import android.app.job.JobParameters
import android.os.AsyncTask
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

// TODO rewrite tests
@RunWith(RobolectricTestRunner::class)
class FretboardJobServiceTest {
    private val TAG = "FretboardConfigLoaderTest"

    @Test
    fun testServiceisWorking() {
        val parameters = Mockito.mock(JobParameters::class.java)
        val task = Mockito.mock(AsyncTask::class.java)
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


