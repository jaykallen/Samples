package com.jaykallen.samples.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity (tableName = "Portfolio")
data class Portfolio (
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "portfolioName") var portfolioName: String,
    @ColumnInfo(name = "stockList") var stockList: String) {

    constructor(): this(0, "", "")

}