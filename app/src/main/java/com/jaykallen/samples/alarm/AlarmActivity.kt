package com.jaykallen.samples.alarm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jaykallen.samples.R
import timber.log.Timber
import java.util.*

class AlarmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        Timber.d(" *** Alarm Activity ***")
    }

    fun onButtonClick(view: View) {
        Timber.d("Alarm set for 1 minutes from ${Date()}")
        AlarmMgr.setAlarm(this)
    }
}
