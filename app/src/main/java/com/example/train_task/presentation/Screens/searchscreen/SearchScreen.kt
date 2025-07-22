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
import androidx.compose.runtime.LaunchedEffect // Import LaunchedEffect


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavHostController, initialBinNumber: Int? = null) { // Add initialBinNumber parameter
    val viewModel = hiltViewModel<SearchScreenViewModel>()

    // Use LaunchedEffect to update the search text and trigger a search
    // only once when the screen is composed and an initialBinNumber is provided from navigation.
    LaunchedEffect(initialBinNumber) {
        if (initialBinNumber != null) {
            val binNumberString = initialBinNumber.toString()
            viewModel.updateSearchTextState(binNumberString) // Update the search text in the AppBar
            viewModel.getBinfo(initialBinNumber) // Automatically trigger the search for the bin number
            // Optionally, you might want to clear the argument from the saved state handle
            // if you don't want it to persist across configuration changes or other navigations
            // that don't explicitly set it. For this specific flow, updating the state once is fine.
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
