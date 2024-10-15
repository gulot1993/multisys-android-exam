package com.android.multisys.exam.feature.navigation

sealed class NavigationRoute(val route: String) {
    data object HomeScreen : NavigationRoute("users_screen")
    data object PostsScreen : NavigationRoute("profile_screen")
    data object AuthScreen : NavigationRoute("auth_screen")
    data object WebViewScreen : NavigationRoute("web_view_screen")
}