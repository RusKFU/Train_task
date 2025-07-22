package com.example.train_task.data.api

import com.example.train_task.data.entities.Binfo
import retrofit2.http.GET
import retrofit2.http.Path

interface BinfoApi {
    @GET("{number}")
    suspend fun getBinfo(
        @Path("number") number: Int
    ): Binfo
}