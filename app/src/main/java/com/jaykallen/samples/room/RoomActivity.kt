package com.jaykallen.samples.room

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jaykallen.stocks.database.RoomInstance
import com.jaykallen.samples.R
import timber.log.Timber

/**
 * Created by jaykallen on 4/24/18.
 * This is a working example of creating a portfolio and querying for it.
 * Uses LiveData to retrieve the data from the Room Manager!
 */

class RoomActivity : AppCompatActivity() {
    private var portfolios: List<Portfolio> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        getData()
    }

    fun getData() {
        val roomMgr = RoomMgr.getPortfolio(this)
        roomMgr.portfolioLive.observe(this, Observer { portfolioLive ->
            Timber.d("Got the values from Live Data")
            portfolios = portfolioLive ?: ArrayList()
            updateUI()
        })
        roomMgr.execute()
    }

    fun updateUI() {
        Timber.d("portfolios retrieved = ${portfolios.size}")
        for (i in 0 until portfolios.size) {
            Timber.d("Portfolio = ${portfolios[i].portfolioName}, stocks=${portfolios[i].stockList}")
        }
    }

    fun createData() {
        val portfolio = Portfolio()
        portfolio.id = 1234
        portfolio.portfolioName = "Breakevens"
        portfolio.stockList = "VICL,AMRN,GRPN"
        val roomMgr = RoomMgr.createPortfolio(this, portfolio)
        roomMgr.execute()
    }
}
