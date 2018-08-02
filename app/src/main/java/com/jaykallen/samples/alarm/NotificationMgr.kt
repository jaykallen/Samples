package com.jaykallen.samples.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.jaykallen.samples.R

/**
 * Created by Jay Kallen on 4/23/18.
 */

class NotificationMgr {
    companion object {
        fun notify(context: Context, appName: String, msg: String) {
            val channelId = "samples_app"
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId, appName, NotificationManager.IMPORTANCE_DEFAULT)
                channel.description = "Samples Channel"
                channel.enableLights(true)
                channel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
                channel.enableVibration(true)
                notificationManager.createNotificationChannel(channel)
            }
            val builder = NotificationCompat.Builder(context, channelId)
                    .setVibrate(longArrayOf(0, 100, 100, 100, 100, 100))
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(appName)
                    .setContentText(msg)
            notificationManager.notify(builder.hashCode(), builder.build())
        }
    }
}