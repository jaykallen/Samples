package com.jaykallen.samples.cursorloader

// Created by Jay Kallen on 1/13/2017.

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.jaykallen.samples.cursorloader.LoadDatabase.Companion.DATABASE_TABLE

class LoadDatabase internal constructor(private val context: Context) {
    private var db: SQLiteDatabase? = null
    private val mDatabaseHelper: DatabaseHelper

    val allRows: Cursor
        get() {
            val cursor = db!!.query(DATABASE_TABLE, All_KEYS, null, null, null, null, KEY_TIME)
            if (cursor.count > 0) {
                cursor.moveToFirst()
            }
            return cursor
        }

    internal inner class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        override fun onCreate(database: SQLiteDatabase) {
            val CREATE_DB_SQL = "CREATE TABLE " + DATABASE_TABLE + " (" +
                    KEY_ROWID + " integer primary key autoincrement, " + KEY_TIME + " text not null, " +
                    KEY_TITLE + " text not null, " + KEY_IMAGE + " text not null, " + KEY_COMPLETE + " text not null );"
            database.execSQL(CREATE_DB_SQL)
        }

        override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            database.execSQL("DROP TABLE IS EXISTS $DATABASE_TABLE")
            onCreate(database)
        }
    }

    init {
        mDatabaseHelper = DatabaseHelper(context)
    }

    fun open(): LoadDatabase {
        db = mDatabaseHelper.writableDatabase
        return this
    }

    fun close() {
        mDatabaseHelper.close()
    }

    fun insertRow(time: String, title: String, image: String): Long {
        val values = ContentValues()
        values.put(KEY_TIME, time)
        values.put(KEY_TITLE, title)
        values.put(KEY_IMAGE, image)
        values.put(KEY_COMPLETE, "F")
        return db!!.insert(DATABASE_TABLE, null, values)
    }

    fun initDB() {
        val mTimes = arrayOf("07:00", "08:30", "10:00", "11:30", "12:00", "13:00", "14:30", "16:00", "16:00", "17:30", "17:50", "19:00", "19:30", "20:30", "21:30")
        val mTitles = arrayOf("Psyllium Shake", "Herbs", "Psyllium Shake", "Herbs", "Pau D'Arco Tea", "Psyllium Shake", "Herbs", "Colonic", "Psyllium Shake", "Herbs", "Cucumber Juice", "Vegetable Broth", "Pau D'Arco Tea", "Herbs", "Probiotic", "Herbs", "Probiotic")
        val mImages = arrayOf("psyllium", "herbs", "psyllium", "herbs", "paudarco", "psyllium", "herbs", "colonic", "psyllium", "herbs", "cucumber", "veggiebroth", "paudarco", "herbs", "probiotic")
        for (i in 0..14) {
            val id = insertRow(mTimes[i], mTitles[i], mImages[i])
        }
    }

    fun deleteAll() {
        val cursor = allRows
        val rowidx = cursor.getColumnIndexOrThrow(KEY_ROWID)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                deleteRow(cursor.getLong(rowidx))
            } while (cursor.moveToNext())
        }
    }

    fun deleteRow(rowid: Long): Boolean {
        val whereStr = "$KEY_ROWID=$rowid"
        return db!!.delete(DATABASE_TABLE, whereStr, null) > 0
    }

    fun getRow(rowid: Long): Cursor {
        val whereStr = "$KEY_ROWID=$rowid"
        val cursor = db!!.query(DATABASE_TABLE, All_KEYS, whereStr, null, null, null, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
        }
        return cursor
    }

    companion object {
        val DATABASE_VERSION = 2
        val DATABASE_NAME = "Sanctuary"
        val DATABASE_TABLE = "Schedule"
        val KEY_ROWID = "_id"
        val KEY_TIME = "time"
        val KEY_TITLE = "title"
        val KEY_IMAGE = "image"
        val KEY_COMPLETE = "complete"
        val COL_ROWID = 0
        val COL_TIME = 1
        val COL_TITLE = 2
        val COL_IMAGE = 3
        val COL_COMPLETE = 4
        val All_KEYS = arrayOf(KEY_ROWID, KEY_TIME, KEY_TITLE, KEY_IMAGE, KEY_COMPLETE)
    }

}
