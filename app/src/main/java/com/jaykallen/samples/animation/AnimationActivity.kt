package com.jaykallen.samples.animation

import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.HapticFeedbackConstants
import android.view.MotionEvent
import android.view.View
import com.jaykallen.samples.R
import kotlinx.android.synthetic.main.activity_animation.*
import timber.log.Timber
import java.util.*

class AnimationActivity : AppCompatActivity() {
    private var clicks = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
    }

    fun onEasterOpenClick(v: View) {
        easterEggOverlay.visibility = View.VISIBLE
        setupEasterEggAnimator()
    }

    private fun setupEasterEggAnimator() {
        val endRunnable = object : Runnable {
            override fun run() {
                val point = Point()
                windowManager.defaultDisplay.getSize(point)
                val rand = Random()
                var x = 0
                var y = 0
                var endY = 0
                var endX = 0
                when (rand.nextInt(4)) {
                    0 -> {
                        x = rand.nextInt(point.x)
                        endY = point.y
                        endX = rand.nextInt(point.x)
                    } //top
                    1 -> {
                        y = rand.nextInt()
                        endY = rand.nextInt(point.y)
                        endX = point.x
                    } //left
                    2 -> {
                        x = point.x
                        y = rand.nextInt(point.y)
                        endY = rand.nextInt(point.y)
                        endX = 0
                    } //right
                    3 -> {
                        x = rand.nextInt(point.x)
                        y = point.y
                        endX = rand.nextInt(point.x)
                        endY = 0
                    } //bottom
                }
                flyingBaby.visibility = View.VISIBLE
                flyingBaby.translationX = x.toFloat()
                flyingBaby.translationY = y.toFloat()
                flyingBaby.animate()
                        .translationX(endX.toFloat())
                        .translationY(endY.toFloat())
                        .withEndAction(this)
                        .setDuration(1750)
                        .start()
            }
        }
        flyingBaby.animate()
                .translationYBy(1000f)
                .translationXBy(1000f)
                .withEndAction(endRunnable)
                .start()
    }

    fun easterEggBlocker(v: View) {
        //do nothin, block clicks
    }

    fun onFlyingBabyTapped(view: View) {
        clicks++
        flyingBaby.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
        statusBar.text = "Number of catches: $clicks / 7"
        Timber.d("clicks: $clicks")
        if (clicks > 6) {
            dragBaby()
        }
    }

    fun dragBaby() {
        statusBar.text = "Fish Caught! Drag the fish home."
        flyingBaby.animate().cancel()
        easterEggOverlay.visibility = View.GONE
        flyingBaby.setOnTouchListener(object : View.OnTouchListener {
            var isDragging = false
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                if (event.action == MotionEvent.ACTION_DOWN) isDragging = true
                else if (event.action == MotionEvent.ACTION_UP) isDragging = false
                if (isDragging) {
                    flyingBaby.y += event.y
                    flyingBaby.x += event.x
                    return true
                } else v.performClick()
                return false
            }
        })
    }


}
