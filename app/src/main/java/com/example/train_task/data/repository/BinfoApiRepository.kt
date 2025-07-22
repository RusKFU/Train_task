package com.example.train_task.data.repository


import com.example.train_task.data.api.BinfoApi
import com.example.train_task.data.entities.Binfo
import javax.inject.Inject

class BinfoApiRepository @Inject constructor(
    private val binfoApi: BinfoApi
) {
    suspend fun getBinfo(number: Int): Binfo {
        return binfoApi.getBinfo(number)
    }
}