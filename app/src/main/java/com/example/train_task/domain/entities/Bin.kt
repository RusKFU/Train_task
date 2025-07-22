package com.example.train_task.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bin(
    val number: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int = number
)