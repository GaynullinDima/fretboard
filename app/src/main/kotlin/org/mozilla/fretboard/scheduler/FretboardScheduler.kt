package org.mozilla.fretboard.scheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import org.mozilla.fretboard.helpers.FretboardConfiguration


interface  FretboardScheduler {
    fun scheduleUpload(configuration: FretboardConfiguration)
}

class JobSchedulerFretboardScheduler : FretboardScheduler {
    val JOB_ID = 42 //  TODO: Make this configurable by client

    override fun scheduleUpload(configuration: FretboardConfiguration) {
        val context = configuration.context
        val jobService = ComponentName(context, FretboardJobService::class.java)

        val jobInfo = JobInfo.Builder(JOB_ID, jobService)
                .setPeriodic(configuration.periodicInterval)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setBackoffCriteria(configuration.initialBackoffForUpload,
                        JobInfo.BACKOFF_POLICY_EXPONENTIAL)
                .build()

        if (context != null) {
            val scheduler = context
                    .getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            scheduler.schedule(jobInfo)
        } // TODO: refactor this code
    }
}