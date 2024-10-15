package com.android.multisys.exam.feature.navigation

sealed class NavigationRoute(val route: String) {
    data object HomeScreen : NavigationRoute("users_screen")
    data object PostsScreen : NavigationRoute("profile_screen")
    data object AuthScreen : NavigationRoute("navigation_screen")
}