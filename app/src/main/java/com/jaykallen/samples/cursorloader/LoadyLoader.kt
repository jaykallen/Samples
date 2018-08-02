package com.jaykallen.samples.cursorloader

// Created by jkallen on 5/18/2017. This supports the Cursor loader attempt.  This plays the role of
// LoadDatabase.

import android.content.Context
import android.database.Cursor
import android.support.v4.content.CursorLoader
import android.util.Log
import timber.log.Timber

class LoadyLoader(context: Context) : CursorLoader(context) {
    private val mLoadDatabase: LoadDatabase

    init {
        Timber.d("Open the Database")
        mLoadDatabase = LoadDatabase(context)
        mLoadDatabase.open()
        //mLoadDatabase.deleteAll();
        //Data should already be in the database
        //mLoadDatabase.initDB();
    }

    override fun loadInBackground(): Cursor {
        //Fetch local data from disk using a Loader on a background thread
        Timber.d("Load Database in background")
        // Anything that gets a cursor
        return mLoadDatabase.allRows
        // the result of this goes to onLoadFinished
    }
}
