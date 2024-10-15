package com.android.multisys.exam.feature.navigation

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.multisys.exam.BuildConfig
import com.android.multisys.exam.feature.auth.AuthScreen
import com.android.multisys.exam.feature.home.HomeScreen
import com.android.multisys.exam.feature.post.PostsScreen
import com.android.multisys.exam.feature.webview.WebViewScreen
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ResponseTypeValues
import timber.log.Timber
import java.util.UUID


@Composable
fun MultisysAndroidExamApp() {
    val navController = rememberNavController()
    val startDestination = NavigationRoute.AuthScreen.route

    val serviceConfig = remember {
        AuthorizationServiceConfiguration(
            Uri.parse(BuildConfig.REDDIT_AUTH_URL),
            Uri.parse(BuildConfig.REDDIT_TOKEN_URL)
        )
    }

    val context = LocalContext.current

    var authorizationCode = remember {
        ""
    }

    var permalink = remember {
        ""
    }

    val resLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result.resultCode == RESULT_OK && result.data != null) {
            val response = AuthorizationResponse.fromIntent(result.data!!)
            if (response?.authorizationCode != null) {
                authorizationCode = response!!.authorizationCode!!
                if (authorizationCode.isNotEmpty()) {
                    navController.navigate(NavigationRoute.HomeScreen.route)
                }
            }
        }
    }

    var subreddit = remember {
        ""
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = NavigationRoute.HomeScreen.route) {
            HomeScreen(
                authorizationCode = authorizationCode,
                onItemClick = {
                    subreddit = it
                    navController.navigate(NavigationRoute.PostsScreen.route)
                }
            )
        }

        composable(
            route = NavigationRoute.PostsScreen.route,
        ) {
            PostsScreen(
                subredditTitle = subreddit,
                onItemClick = {
                    permalink = it
                    navController.navigate(NavigationRoute.WebViewScreen.route)
                }
            )
        }

        composable(route = startDestination) {
            val intent = startAuthorization(serviceConfig, context)
            AuthScreen(
                onClick = { resLauncher.launch(intent) }
            )
        }

        composable(route = NavigationRoute.WebViewScreen.route) {
            WebViewScreen(
                link = permalink
            )
        }

    }
}

fun startAuthorization(serviceConfig: AuthorizationServiceConfiguration, context: Context): Intent {
    val request = AuthorizationRequest
        .Builder(
            serviceConfig,
            BuildConfig.REDDIT_CLIENT_ID,
            ResponseTypeValues.CODE,
            Uri.parse(BuildConfig.REDDIT_REDIRECT_URI),
        )
        .setState(UUID.randomUUID().toString())
        .setScope("read")
        .build()

    val authService = AuthorizationService(context)
    val authIntent = authService.getAuthorizationRequestIntent(
        request
    )
    return authIntent
}