package org.mozilla.fretboard

import android.app.job.JobInfo
import android.content.Context.JOB_SCHEDULER_SERVICE
import android.app.job.JobInfo.BACKOFF_POLICY_EXPONENTIAL
import android.app.job.JobInfo.NETWORK_TYPE_ANY
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context


interface  FretboardScheduler {
    fun scheduleUpload(configuration : FretboardConfiguration )
}

class JobSchedulerFretboardScheduler : FretboardScheduler{
    val JOB_ID = 42 //  TODO: Make this configurable (Issue #24)

    override fun scheduleUpload(configuration: FretboardConfiguration) {
        val jobService = ComponentName(configuration.getContext(), FretboardJobService::class.java)

        val jobInfo = JobInfo.Builder(JOB_ID, jobService)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setBackoffCriteria(configuration.getInitialBackoffForUpload(), JobInfo.BACKOFF_POLICY_EXPONENTIAL)
                .build()

        val scheduler = configuration.getContext()
                .getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

        scheduler.schedule(jobInfo)
    }
}