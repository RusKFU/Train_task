package com.example.train_task.presentation.Screens.historyscreen

import com.example.train_task.domain.entities.Bin

sealed class HistoryScreenEvent {
    data class DeleteBin(val bin: Bin) : HistoryScreenEvent()
    object RestoreBin : HistoryScreenEvent()
}
