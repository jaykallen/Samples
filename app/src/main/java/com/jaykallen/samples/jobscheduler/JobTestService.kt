package com.jaykallen.samples.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.os.AsyncTask
import android.util.Log
import timber.log.Timber

// Created by jaykallen on 5/24/17.  Requires Minimum API of 21.
// You have to add android.permission.BIND_JOB_SERVICE to the manifest to get this to work.
// onStopJob never gets executed.

class JobTestService : JobService() {
    private var params: JobParameters? = null
    private var doIt: DoItTask? = null

    override fun onStartJob(params: JobParameters): Boolean {
        this.params = params
        Timber.d("onStartJob")
        doIt = DoItTask()
        doIt!!.execute()
        return false
    }

    override fun onStopJob(params: JobParameters): Boolean {
        Timber.d("onStopJob")
        if (doIt != null)
            doIt!!.cancel(true)
        return false
    }

    private inner class DoItTask : AsyncTask<Void, Void, Void>() {
        override fun onPostExecute(aVoid: Void) {
            super.onPostExecute(aVoid)
            Timber.d("onPostExecute")
            jobFinished(params, false)
        }

        override fun doInBackground(vararg params: Void): Void? {
            Timber.d("doInBackground")
            return null
        }
    }
}
