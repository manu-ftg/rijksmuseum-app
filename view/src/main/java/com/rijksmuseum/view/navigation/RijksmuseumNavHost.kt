package com.rijksmuseum.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
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
            HomeScreen(
                navigateToDetailScreen = { objectId ->
                    navController.navigate(
                        AppNavigation.Details.route.replace(
                            "{objectId}",
                            objectId
                        )
                    ) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            AppNavigation.Details.route
        ) {
            DetailsScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}

fun NavBackStackEntry?.isBackButtonVisible(): Boolean {
    return this?.destination?.route != null && this.destination.route != AppNavigation.Home.route
}
