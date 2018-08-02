package com.jaykallen.samples.eventbus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jaykallen.samples.R
import org.greenrobot.eventbus.EventBus

class EventBusActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus3)
    }

    fun onThirdClick(view: View) {
        EventBus.getDefault().post(EventBusTrigger("Hello EventBus!"))
    }
}
