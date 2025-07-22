package com.example.train_task.data.db

import androidx.room.*
import com.example.train_task.domain.entities.Bin

import androidx.room.*

@Dao
interface BinfoDao {
    @Query("SELECT * FROM bin")
    fun getBins(): List<Bin>

    @Query("SELECT * FROM bin WHERE number = :number")
    suspend fun getBin(number: Int): Bin?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBin(bin: Bin)

    @Delete
    suspend fun deleteBin(bin: Bin)
}
