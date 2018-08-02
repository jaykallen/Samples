package com.jaykallen.samples.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView

import com.jaykallen.samples.R

// Created by jaykallen on 5/24/17. This is an attempt to get the Job Scheduler working.
// JobService handles asynchronous requests that were previously scheduled.
// Requires:         <!--
//<activity android:name=".JobActivity">
//<intent-filter>
//<action android:name="android.intent.action.MAIN" />
//<category android:name="android.intent.category.LAUNCHER" />
//</intent-filter>
//</activity>
//<service android:name=".JobTestService"
//android:permission="android.permission.BIND_JOB_SERVICE"
//android:exported="true"/> -->

class JobActivity : AppCompatActivity() {
    private val tag = "TagJobActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job)
        val tv1 = findViewById<View>(R.id.textView1) as TextView
        Log.d(tag, "onCreate")
        tv1.text = "Click the button to start Jobservice"
    }

    fun startProgress(view: View) {
        Log.d(tag, "startProgress")
        val jobScheduler = applicationContext.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        val componentName = ComponentName(applicationContext, JobTestService::class.java)
        val jobInfo = JobInfo.Builder(1, componentName).setOverrideDeadline(10).setRequiresCharging(true).build()
        jobScheduler.schedule(jobInfo)
    }

}
