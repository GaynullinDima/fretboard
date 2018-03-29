package org.mozilla.fretboard.sheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.os.AsyncTask
import android.support.annotation.VisibleForTesting
import android.util.Log
import org.mozilla.fretboard.config.FretboardConfiguration
import org.mozilla.fretboard.net.HttpURLConnectionConfigLoader
import org.mozilla.fretboard.storage.SharedPreferenceConfigStorage
import java.net.ServerSocket


class FretboardJobService : JobService {
    private val TAG = "FretboardJobService"

    private val PREFERENCE_UPLOAD_COUNT_PREFIX = "upload_count_"
    private val PREFERENCE_LAST_UPLOAD_PREFIX = "last_upload_"
    private val context: Context

    private var loadTask: LoadExperimentTask? = null

    init {
    }

    constructor(context: Context) {
        this.context = context
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        loadTask = LoadExperimentTask()
        loadTask!!.execute(params)
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        if (loadTask != null) {
            loadTask!!.cancel(true)
        }
        return true;
    }

    private inner class LoadExperimentTask : AsyncTask<JobParameters, Void, Void>() {
        val defaultPath: String = "/v1/buckets/default/collections/tasks/records"


         override fun doInBackground(vararg params: JobParameters): Void? {
            val parameters = params[0]
            loadExperimentsInBackground(defaultPath, this, parameters)
            return null
        }
    }

    @VisibleForTesting
    fun loadExperimentsInBackground(defaultPath: String,
                                    task: AsyncTask<*, *, *>, parameters: JobParameters) {
        val config = FretboardConfiguration()
        config.setContext(context)
        val storage = SharedPreferenceConfigStorage()
        val loader = HttpURLConnectionConfigLoader()

        Log.d(TAG, "Performing load of experiment")
        if (task.isCancelled) {
            Log.d(TAG, "Job stopped. Exiting.")
            return  // Job will be rescheduled from onStopJob().
        }
        val result : String? = loader.loadConfig(config, defaultPath)
        println("Result: $result")
        Log.d(TAG, "Result: $result")
        storage.setExperimentJson(config, result!!)

        Log.d(TAG, "All loads performed")
        val resultFromStorage = storage.getExperimentJson(config)
        println("Hello: $resultFromStorage")
        //jobFinished(parameters, false)
    }

    // TODO Added for architecture reference. Refactor
    /*

    fun LoadExperimentsInBackground(task: AsyncTask<*, *, *>, parameters: JobParameters) {
        // TODO add holder for configuartion and refactor storage and all that
        val configuration = FretboardConfiguration()
        val storage = SharedPreferenceConfigStorage()

        for (builder in telemetry.getBuilders()) {
            val pingType = builder.getType()
            Log.d(TAG, "Performing upload of ping type: $pingType")

            if (task.isCancelled) {
                Log.d(TAG, "Job stopped. Exiting.")
                return  // Job will be rescheduled from onStopJob().
            }

            if (storage.countStoredPings(pingType) === 0) {
                Log.d(TAG, "No pings of type $pingType to upload")
                continue
            }

            if (hasReachedUploadLimit(configuration, pingType)) {
                Log.d(TAG, "Daily upload limit for type $pingType reached")
                continue
            }

            if (!performPingUpload(telemetry, pingType)) {
                Log.i(TAG, "Upload aborted. Rescheduling job if limit not reached.")
                jobFinished(parameters, !hasReachedUploadLimit(configuration, pingType))
                return
            }
        }

        Log.d(TAG, "All uploads performed")
        jobFinished(parameters, false)
    }
    */

}