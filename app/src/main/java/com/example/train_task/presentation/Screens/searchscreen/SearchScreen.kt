package com.example.train_task.presentation.Screens.searchscreen

import android.annotation.SuppressLint

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.train_task.presentation.Screens.searchscreen.components.BinDataCard
import com.example.train_task.presentation.Screens.searchscreen.components.SearchAppBar
import com.example.train_task.presentation.navigation.Screens
import androidx.compose.runtime.LaunchedEffect


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavHostController, initialBinNumber: Int? = null) {
    val viewModel = hiltViewModel<SearchScreenViewModel>()


    LaunchedEffect(initialBinNumber) {
        if (initialBinNumber != null) {
            val binNumberString = initialBinNumber.toString()
            viewModel.updateSearchTextState(binNumberString)
            viewModel.getBinfo(initialBinNumber)
        }
    }

    val searchTextState by viewModel.searchTextState
    val binfo = viewModel.binfo.observeAsState().value

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {
                navController.navigate(Screens.HistoryScreen.route)
            },
        ) {
            Icon(
                imageVector = Icons.Default.History,
                tint = Color.White,
                contentDescription = "History"
            )
        }
    },
        topBar = {
            SearchAppBar(
                text = searchTextState,
                onTextChange = {
                    viewModel.updateSearchTextState(newValue = it)
                },
                viewModel = viewModel
            )
        }
    ) {
        BinDataCard(binfo = binfo)
    }
}
