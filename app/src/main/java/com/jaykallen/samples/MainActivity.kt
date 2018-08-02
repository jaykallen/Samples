package com.jaykallen.samples

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.common.api.internal.LifecycleActivity
import com.jaykallen.samples.deeplinking.DeepLinkActivity
import com.jaykallen.samples.alarm.AlarmActivity
import com.jaykallen.samples.animation.AnimationActivity
import com.jaykallen.samples.aynctask.AsyncActivity
import com.jaykallen.samples.cursorloader.LoadyActivity
import com.jaykallen.samples.eventbus.EventBusActivity
import com.jaykallen.samples.firebase.FirebaseActivity
import com.jaykallen.samples.fragment.FragActivity
import com.jaykallen.samples.jobscheduler.JobActivity
import com.jaykallen.samples.mvp.MvpActivity
import com.jaykallen.samples.recycler.RecyclerActivity
import com.jaykallen.samples.room.RoomActivity
import com.jaykallen.samples.rxjava.RxjavaActivity
import com.jaykallen.samples.service.ServiceActivity
import com.jaykallen.samples.viewpager.ViewPagerActivity
import kotlinx.android.synthetic.main.dialog_yesno.*
import timber.log.Timber
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onAlarmClick(view: View) {
        startActivity(Intent(this, AlarmActivity::class.java))
    }

    fun onAnimationClick(view: View) {
        startActivity(Intent(this, AnimationActivity::class.java))
    }

    fun onAsyncTaskClick(view: View) {
        startActivity(Intent(this, AsyncActivity::class.java))
    }

    fun onCursorLoaderClick(view: View) {
        startActivity(Intent(this, LoadyActivity::class.java))
    }

    fun onEventBusClick(view: View) {
        startActivity(Intent(this, EventBusActivity::class.java))
    }

    fun onDeepLinkingClick(view: View) {
        startActivity(Intent(this, DeepLinkActivity::class.java))
    }

    fun onFirebaseClick(view: View) {
        startActivity(Intent(this, FirebaseActivity::class.java))
    }

    fun onFragmentClick(view: View) {
        startActivity(Intent(this, FragActivity::class.java))
    }

    fun onJobSchedulerClick(view: View) {
        startActivity(Intent(this, JobActivity::class.java))
    }

    fun onLifeCycleClick(view: View) {
        startActivity(Intent(this, LifecycleActivity::class.java))
    }

    fun onMvpClick(view: View) {
        startActivity(Intent(this, MvpActivity::class.java))
    }

    fun onRecyclerClick(view: View) {
        startActivity(Intent(this, RecyclerActivity::class.java))
    }

    fun onRoomClick(view: View) {
        startActivity(Intent(this, RoomActivity::class.java))
    }

    fun onRxJavaClick(view: View) {
        startActivity(Intent(this, RxjavaActivity::class.java))
    }

    fun onServiceClick(view: View) {
        startActivity(Intent(this, ServiceActivity::class.java))
    }

    fun onViewPagerClick(view: View) {
        startActivity(Intent(this, ViewPagerActivity::class.java))
    }

    fun onDialogYesNoClick(view: View) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_yesno)
        dialog.setTitle("Sand Kotlin")
        dialog.messageTextView.text = "Are you done looking at this dialog box?"
        dialog.yesLayout.setOnClickListener {
            Timber.d("Yes Clicked")
            dialog.dismiss()
        }
        dialog.noLayout.setOnClickListener {
            Timber.d("No Clicked")
            dialog.dismiss()
        }
        dialog.show()
    }

    fun onDialogOkClick(view: View) {
        AlertDialog.Builder(this)
                .setMessage("Please click OK. OK?")
                .setPositiveButton("OK", {dialog, which -> dialog.dismiss() })
                .show()
    }

    fun onButtonClick(view: View) {
        Timber.d("Example of forcible catch")
        try {
            testThrow()
            throw Exception("Forced Catch")
        } catch (e: Exception) {
            Timber.d("Catch: " + e.message)
        }
    }

    @Throws(IOException::class)
    private fun testThrow() {
        val string: String? = null
    }

    private fun setDate(year: Int, month: Int, day: Int): Date {
        val greg = GregorianCalendar()
        greg.time = Date()
        greg.set(Calendar.SECOND, 0)
        greg.set(Calendar.MILLISECOND, 0)
        greg.set(year, month - 1, day)
        return greg.time
    }

    private fun testHashCode() {
        val jay = "Jay"
        val kallen = "Kallen"
        val brea = "Brea CA"
        val cali = "California"
        val biggy = "This is a really really long string, so will it have an impact on the hash code"
        Timber.d(jay + " = " + jay.hashCode())
        Timber.d(kallen + " = " + kallen.hashCode())
        Timber.d(brea + " = " + brea.hashCode())
        Timber.d(cali + " = " + cali.hashCode())
        Timber.d(biggy + " = " + biggy.hashCode())
    }

    private fun testTreeMap() {
        val treemap = TreeMap<Int, String>()
        treemap.put(0, "Zero")
        treemap.put(1, "One")
        treemap.put(3, "Three")
        treemap.put(2, "Two")
        for (i in 0 until treemap.size) {
            Timber.d(" " + treemap[i])
        }
        Timber.d("Testing String.format")
        val test = String.format("%s - %d %s", "test1", 45, "entries")
        Timber.d("results: " + test)
    }
}
