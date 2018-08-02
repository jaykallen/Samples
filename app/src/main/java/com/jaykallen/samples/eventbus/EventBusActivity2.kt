package com.jaykallen.samples.eventbus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jaykallen.samples.R
import org.greenrobot.eventbus.EventBus

class EventBusActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus2)
    }

    fun onSecondClick(view: View) {
        EventBus.getDefault().post(EventBusTrigger("Hello EventBus!"))
    }

    fun onGoto3Click(view: View) {
        val intent = Intent(this, EventBusActivity3::class.java)
        startActivity(intent)
    }

}
