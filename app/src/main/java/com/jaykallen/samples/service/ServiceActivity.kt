package com.jaykallen.samples.service

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

import com.jaykallen.samples.R

// This is a test of the Service library.  The service can be launched and will display log messages
// once every 3 seconds, but the UI still remains responsive, which is proven by clicking the response
// button.
// Requires:         <!--  <service android:name=".ServiceTest"/> -->

class ServiceActivity : AppCompatActivity() {
    private val tag = "TagServiceActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
    }

    fun onServiceClick(view: View) {
        Log.d(tag, "Launching Service")
        val intent = Intent(this, ServiceTest::class.java)
        startService(intent)
    }

    fun onRespondClick(view: View) {
        Log.d(tag, "Responsive")
    }

}
