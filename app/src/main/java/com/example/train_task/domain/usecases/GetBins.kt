package com.example.train_task.domain.usecases

import com.example.train_task.domain.entities.Bin
import com.example.train_task.domain.repository.BinfoRepository


class GetBins(private val repository: BinfoRepository) {
    suspend operator fun invoke(): List<Bin> {
        return repository.getBins()
    }
}