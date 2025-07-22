package com.example.train_task.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.train_task.presentation.Screens.historyscreen.HistoryScreen
import com.example.train_task.presentation.Screens.searchscreen.SearchScreen


sealed class Screens(val route: String) {
    // Modify SearchScreen to accept an optional binNumber argument
    object SearchScreen : Screens(route = "search_screen?binNumber={binNumber}") {
        fun createRoute(binNumber: Int? = null): String {
            return if (binNumber != null) "search_screen?binNumber=$binNumber" else "search_screen"
        }
    }
    object HistoryScreen : Screens(route = "history_screen")
}

@Composable
fun SetupNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.SearchScreen.route
    ) {
        composable(route = Screens.SearchScreen.route) { backStackEntry ->
            // Retrieve the binNumber from navigation arguments
            val binNumber = backStackEntry.arguments?.getString("binNumber")?.toIntOrNull()
            SearchScreen(navController = navController, initialBinNumber = binNumber)
        }

        composable(route = Screens.HistoryScreen.route)
        {
            HistoryScreen(navController = navController)
        }
    }
}
