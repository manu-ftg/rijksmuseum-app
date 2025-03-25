package com.rijksmuseum.view.navigation

sealed class AppNavigation(val route: String) {
    object Home : AppNavigation(route = "home_screen")
    object Details : AppNavigation(route = "detail_screen/{objectId}")
}
