package com.jaykallen.samples.cursorloader

// Created by Jay Kallen on 5/18/2017.  Attempt to use CursorLoader to access data from MS SQL or SQLite

import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v4.widget.SimpleCursorAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jaykallen.samples.R
import timber.log.Timber

class LoadyActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {
    internal var mAdapter: SimpleCursorAdapter? = null
    private val mLoadDatabase: LoadDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cursor_loader)
        Timber.d("Activity started and Loader initialized")
        supportLoaderManager.initLoader(1932, null, this)
    }

    fun onButtonClick(view: View) {
        // Do nothing for now.
    }

    override fun onCreateLoader(i: Int, bundle: Bundle?): Loader<Cursor> {
        Timber.d("onCreateLoader")
        return LoadyLoader(this)
    }

    override fun onLoadFinished(cursorLoader: Loader<Cursor>, cursor: Cursor) {
        //Propagate data changes through a Loader to the UI
        Timber.d("onLoadFinished")
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                val id = cursor.getString(LoadDatabase.COL_ROWID)
                val time = cursor.getString(LoadDatabase.COL_TIME)
                val title = cursor.getString(LoadDatabase.COL_TITLE)
                val image = cursor.getString(LoadDatabase.COL_IMAGE)
                val complete = cursor.getString(LoadDatabase.COL_COMPLETE)
                Timber.d("id:$id, time:$time, title:$title, image:$image, complete:$complete")
            } while (cursor.moveToNext())
        }
        cursor.close()
    }

    override fun onLoaderReset(cursorLoader: Loader<Cursor>) {
        Timber.d("onLoaderReset")
        mAdapter!!.swapCursor(null)
    }

}
