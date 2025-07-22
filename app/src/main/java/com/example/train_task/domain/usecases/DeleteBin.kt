package com.example.train_task.domain.usecases

import com.example.train_task.domain.entities.Bin
import com.example.train_task.domain.repository.BinfoRepository


class DeleteBin(private val repository: BinfoRepository) {
    suspend operator fun invoke(bin: Bin) {
        return repository.deleteBin(bin)
    }
}