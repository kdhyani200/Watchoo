package kd.dhyani.project0024.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kd.dhyani.project0024.ui.home.HomeScreen
import kd.dhyani.project0024.ui.details.DetailsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("details/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toLongOrNull() ?: 0L
            DetailsScreen(id)
        }
    }
}
