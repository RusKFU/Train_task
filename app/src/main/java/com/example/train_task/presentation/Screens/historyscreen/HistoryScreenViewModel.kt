package com.example.train_task.presentation.Screens.historyscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.train_task.domain.entities.Bin
import com.example.train_task.domain.usecases.GetBins
import com.example.train_task.domain.usecases.InsertBin
import com.example.train_task.domain.usecases.DeleteBin

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryScreenViewModel @Inject constructor(
    private val getBinsUseCase: GetBins,
    private val deleteBinUseCase: DeleteBin,
    private val insertBinUseCase: InsertBin
) : ViewModel() {
    private var recentlyDeletedNumber: Bin? = null

    private val _bins = MutableLiveData<List<Bin>>()
    val bins: LiveData<List<Bin>> = _bins

    init {
        getBins()
    }

    private fun getBins() {
        viewModelScope.launch {
            getBinsUseCase().let {
                _bins.postValue(it)
            }
        }
    }

    fun onEvent(event: HistoryScreenEvent) {
        when (event) {
            is HistoryScreenEvent.DeleteBin -> {
                viewModelScope.launch {
                    deleteBinUseCase(event.bin)
                    recentlyDeletedNumber = event.bin
                }
            }
            is HistoryScreenEvent.RestoreBin -> {
                viewModelScope.launch {
                    insertBinUseCase(recentlyDeletedNumber ?: return@launch)
                    recentlyDeletedNumber = null
                }
            }
        }
    }
}