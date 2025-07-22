package com.example.train_task.data.repository


import com.example.train_task.data.db.BinfoDao
import com.example.train_task.domain.entities.Bin
import com.example.train_task.domain.repository.BinfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BinfoDatabaseRepository @Inject constructor(
    private val dao: BinfoDao
) : BinfoRepository {
    override suspend fun getBins(): List<Bin> {
        return withContext(Dispatchers.IO) { dao.getBins() }
    }

    override suspend fun getBin(number: Int): Bin? {
        return withContext(Dispatchers.IO) { dao.getBin(number) }
    }

    override suspend fun insertBin(bin: Bin) {
        return withContext(Dispatchers.IO) { dao.insertBin(bin) }
    }

    override suspend fun deleteBin(bin: Bin) {
        return withContext(Dispatchers.IO) { dao.deleteBin(bin) }
    }
}