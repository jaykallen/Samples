package com.jaykallen.samples.aynctask

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jaykallen.samples.R
import kotlinx.android.synthetic.main.activity_async.*

// Jay Kallen 5/18/17: Simple Demonstration of using the AsyncTask method.
// 4/29/18: Updated to Kotlin.

class AsyncActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)
    }

    // Based upon onClick event from layout
    fun startProgress(view: View) {
        startThread().execute("Execute")
    }

    private inner class startThread : AsyncTask<String, Int, String>() {
        override fun onPreExecute() {
            textView1.text = "Preparing"
        }

        override fun doInBackground(vararg params: String): String {
            val execute = params[0]
            for (i in 0..10) {
                try {
                    Thread.sleep(1000)
                    publishProgress(i)
                } catch (e: InterruptedException) {
                    Thread.interrupted()
                }

            }
            return "Completed"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            textView1.text = "Updating"
            progressBar1.progress = values[0]?:0
        }

        override fun onPostExecute(result: String) {
            textView1.text = result
        }
    }


}