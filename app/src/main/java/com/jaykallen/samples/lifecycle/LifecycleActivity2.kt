package com.jaykallen.samples.lifecycle

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

import com.jaykallen.samples.R


class LifecycleActivity2 : AppCompatActivity() {
    private val tag = "TagLifecycleActivity2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle2)
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
        val intent = Intent(this, LifecycleActivity::class.java)
        startActivity(intent)
    }

    fun onExitClick(view: View) {
        finish()
    }
}
