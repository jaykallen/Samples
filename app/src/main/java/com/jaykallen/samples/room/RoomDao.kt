package com.jaykallen.stocks.database

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jaykallen.samples.room.Portfolio

@Dao
interface RoomDao {

    @Query ("Select * from Portfolio")
    fun getAll(): List<Portfolio>

    @Insert (onConflict = REPLACE)
    fun create (portfolio: Portfolio)

    @Update
    fun update (portfolio: Portfolio)

    @Delete
    fun delete (portfolio: Portfolio)

}