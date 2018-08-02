package com.jaykallen.samples

import android.app.Application
import timber.log.Timber

/**
 * Created by Jay Kallen on 5/22/18.
 */
class StartApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t:Throwable?) {
                super.log(priority, tag!! + ".JK", message, t)
            }
        })
    }
}