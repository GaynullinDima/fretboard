package org.mozilla.fretboard.sheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import org.mozilla.fretboard.config.FretboardConfiguration


interface  FretboardScheduler {
    fun scheduleUpload(configuration : FretboardConfiguration)
}

class JobSchedulerFretboardScheduler : FretboardScheduler {
    val JOB_ID = 42 //  TODO: Make this configurable by client

    override fun scheduleUpload(configuration: FretboardConfiguration) {
        val jobService = ComponentName(configuration.getContext(), FretboardJobService::class.java)

        val jobInfo = JobInfo.Builder(JOB_ID, jobService)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setBackoffCriteria(configuration.getInitialBackoffForUpload(), JobInfo.BACKOFF_POLICY_EXPONENTIAL)
                .build()

        val context = configuration.getContext()
        if (context != null) {
            val scheduler = context
                    .getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            scheduler.schedule(jobInfo)
        } // TODO: refactor this code
    }
}