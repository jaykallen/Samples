package com.jaykallen.samples.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.View
import com.jaykallen.samples.R

// 6-5-2017: Basic test of fragments and what happens in the lifecycle during the launching / quitting.
// I guess you can't control a button from the fragment, you have to program it from the activity.
// 2018-05-22: Updated to Kotlin

class FragActivity : FragmentActivity() {
    private val tag = "TagFragActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frag)

        Log.d(tag, "Fragment is being created by the activity")
        val fm = supportFragmentManager
        var fragment: Fragment? = fm.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = FragFragment()
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }

    fun onExitClick(view: View) {
        Log.d(tag, "onExitClick")
        finish()
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


}
