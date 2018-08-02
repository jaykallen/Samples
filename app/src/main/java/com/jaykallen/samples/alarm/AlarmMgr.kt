package com.jaykallen.samples.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import timber.log.Timber
import java.util.*

/**
 * Created by Jay Kallen on 4/23/18.
 * Requires manifest <uses-permission android:name="com.android.alarm.permission.SET_ALARM" />
 * and <receiver android:name=".AlarmMgr"/> in <application>
 */

class AlarmMgr: BroadcastReceiver()  {
    override fun onReceive(context: Context?, intent: Intent?) {
        Timber.d("Broadcast Received")
        if (intent != null && context != null) {
            Timber.d("Alarm Received. So launch notification")
            val extra = intent.getStringExtra("Alarm")
            NotificationMgr.notify(context, "Baby Nursing", extra)
        }
    }

    companion object {
        private lateinit var alarmManager: AlarmManager

        fun setAlarm(context: Context) {
            alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent("android.media.action.DISPLAY_NOTIFICATION")
            intent.addCategory("android.intent.category.DEFAULT")
            intent.putExtra("Alarm", "Jay says wake up!!!")
            intent.setClass(context, AlarmMgr::class.java)
            val id = 1
            val broadcast = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val cal = Calendar.getInstance()
            cal.add(Calendar.MINUTE, 1)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.timeInMillis, broadcast)
            Timber.d("Alarm set for ${cal}")
        }

        fun setAlarm2(context: Context) {
            // This was taken from original Java test method of doing Alarm. Seems to be the same
            val time = GregorianCalendar().timeInMillis + 60 * 1000  // one minute
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?
            val intent = Intent(context, AlarmMgr::class.java)
            alarmManager!!.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(context, 1, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT))
            Toast.makeText(context, "Alarm Scheduled for one minute", Toast.LENGTH_LONG).show()
        }

        fun cancelAlarm(context: Context, id: Int) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val notificationIntent = Intent("android.media.action.DISPLAY_NOTIFICATION")
            notificationIntent.addCategory("android.intent.category.DEFAULT")
            val broadcast = PendingIntent.getBroadcast(context, id, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            alarmManager.cancel(broadcast)
        }

    }
}