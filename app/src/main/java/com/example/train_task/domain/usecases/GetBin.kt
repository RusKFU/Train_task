package com.example.train_task.domain.usecases

import com.example.train_task.domain.entities.Bin
import com.example.train_task.domain.repository.BinfoRepository


class GetBin(private val repository: BinfoRepository) {
    suspend operator fun invoke(bin: Int): Bin? {
        return repository.getBin(bin)
    }
}