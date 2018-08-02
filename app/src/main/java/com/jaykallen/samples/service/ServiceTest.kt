package com.jaykallen.samples.service

import android.app.IntentService
import android.content.Intent
import android.util.Log

/**
 * Created by jaykallen on 5/10/17.
 */

class ServiceTest : IntentService("ServiceTest") {

    private val tag = "TagServiceActivity"

    override fun onHandleIntent(intent: Intent?) {
        try {
            Thread.sleep(1000)
            Log.d(tag, "The Service is running 1")
            Thread.sleep(1000)
            Log.d(tag, "The Service is running 2")
            Thread.sleep(1000)
            Log.d(tag, "The Service is running 3")
        } catch (e: Exception) {
            Log.e(tag, "Problem " + e.message)
        }

    }
}
