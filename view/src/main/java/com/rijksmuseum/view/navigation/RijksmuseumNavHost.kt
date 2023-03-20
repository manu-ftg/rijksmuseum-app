package com.rijksmuseum.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rijksmuseum.view.screen.DetailsScreen
import com.rijksmuseum.view.screen.HomeScreen

@Composable
fun RijksmuseumNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppNavigation.Home.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            AppNavigation.Home.route
        ) {
            HomeScreen { objectId ->
                navController.navigate(AppNavigation.Details.route.replace("{objectId}", objectId))
            }
        }

        composable(
            AppNavigation.Details.route
        ) { backStackEntry ->
            DetailsScreen(backStackEntry.arguments?.getString("objectId") ?: "")
        }
    }
}

sealed class AppNavigation(val route: String) {
    object Home : AppNavigation(route = "home_screen")
    object Details : AppNavigation(route = "detail_screen/{objectId}")
}
