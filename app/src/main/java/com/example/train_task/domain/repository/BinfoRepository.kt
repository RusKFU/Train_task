package com.example.train_task.domain.repository

import com.example.train_task.domain.entities.Bin


interface BinfoRepository {
    suspend fun getBins(): List<Bin>
    suspend fun getBin(number: Int): Bin?
    suspend fun insertBin(bin: Bin)
    suspend fun deleteBin(bin: Bin)
}