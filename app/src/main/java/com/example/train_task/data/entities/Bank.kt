package com.example.train_task.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bank(
    @Json(name = "city")
    val city: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "phone")
    val phone: String?,
    @Json(name = "url")
    val url: String?
)