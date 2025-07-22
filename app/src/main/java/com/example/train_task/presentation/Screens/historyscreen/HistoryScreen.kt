package com.example.train_task.presentation.Screens.historyscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController // Import NavController
import com.example.train_task.presentation.Screens.historyscreen.components.BinItem
import com.example.train_task.presentation.navigation.Screens // Import Screens
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HistoryScreen(navController: NavController) { // Add NavController parameter
    val historyViewModel = hiltViewModel<HistoryScreenViewModel>()
    // We no longer directly interact with SearchScreenViewModel here for updating text
    val bins = historyViewModel.bins.observeAsState(listOf()).value
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "История запросов",
                    color = Color.DarkGray,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(
                    count = bins.size,
                    key = { index -> bins[index].id }
                ) { index ->
                    val bin = bins[index]
                    BinItem(
                        bin = bin,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        onDeleteClick = {
                            historyViewModel.onEvent(HistoryScreenEvent.DeleteBin(bin))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Bin deleted",
                                    actionLabel = "Undo"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    historyViewModel.onEvent(HistoryScreenEvent.RestoreBin)
                                }
                            }
                        },
                        onCardClick = { binNumber -> // Handle card click
                            // Navigate back to SearchScreen with the binNumber as an argument
                            navController.navigate(Screens.SearchScreen.createRoute(binNumber)) {
                                popUpTo(Screens.SearchScreen.route) { inclusive = true } // Clear back stack to SearchScreen
                                launchSingleTop = true // Avoid creating multiple copies
                            }
                        }
                    )
                }
            }
        }
    }
}
