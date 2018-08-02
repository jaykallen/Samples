package com.jaykallen.samples.lifecycle

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.jaykallen.samples.R

// 5-30-17: The purpose of this test is to see how the lifecycle activities trigger
// Test 1: Activity 2 moves on top of Activity 1
// onPause gets executed immediately.
// onStop gets called, but takes 9 seconds to activate.
// onDestroy doesn't get activated at all.
// Test 2: Activity 1 calls Activity 2 and then finishes.
// onPause gets executed immediately.
// onStop gets called, but takes 9 seconds to activate.
// onDestroy gets called, but takes 9 seconds to activate.

class LifecycleActivity : AppCompatActivity() {
    private val tag = "TagLifecycleActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        Log.d(tag, "onCreate")
    }

    public override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    public override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    public override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }

    public override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }

    public override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }

    fun onButton1Click(view: View) {
        val intent = Intent(this, LifecycleActivity2::class.java)
        startActivity(intent)
        finish()
    }

    fun onExitClick(view: View) {
        finish()
    }
}
