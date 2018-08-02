package com.jaykallen.samples.room

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import com.jaykallen.stocks.database.RoomInstance
import timber.log.Timber

// 5/1/18: Finally got this working correctly

class RoomMgr {

    class getPortfolio(context: Context) : AsyncTask<String, String, String>() {
        var portfolioLive: MutableLiveData<List<Portfolio>> = MutableLiveData()
        var portfolioList: List<Portfolio> = ArrayList()
        private val context = context

        override fun doInBackground(vararg p0: String?): String {
            val roomMgr = RoomInstance.getInstance(context)
            portfolioList = roomMgr?.daoAccess()?.getAll()?:ArrayList()
            return "portfolios retrieved = ${portfolioList.size}"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Timber.d(result)
            portfolioLive.value = portfolioList
        }
    }

    class createPortfolio(context: Context, portfolio: Portfolio)  : AsyncTask<String, String, String>() {
        private val context = context
        private val portfolio = portfolio

        override fun doInBackground(vararg p0: String?): String {
            val databaseMgr = RoomInstance.getInstance(context)
            databaseMgr?.daoAccess()?.create(portfolio)
            Timber.d("portfolios added")
            return ""
        }
    }

}