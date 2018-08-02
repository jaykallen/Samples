package com.jaykallen.samples.eventbus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.jaykallen.samples.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber

// This is a test of the Eventbus.  This activity will stay alive while a 2nd activity is called.
// The 2nd activity will trigger events on the eventbus which will be received by the 1st activity.
// Even a 3rd activity can be launched and the 1st activity can still receive events from it.
// Requires: compile 'org.greenrobot:eventbus:3.0.0'
// 4/30/18: Converted to Kotlin and verified to be working!! Nice Job Jay.

class EventBusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus)
    }

    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: EventBusTrigger) {
        Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
        Timber.d("Yes Event Bus is triggering on the Main Activity")
    }

    fun onMainClick(view: View) {
        val intent = Intent(this, EventBusActivity2::class.java)
        startActivity(intent)
    }
}
