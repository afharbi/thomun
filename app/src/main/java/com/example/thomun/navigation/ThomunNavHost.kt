package com.example.thomun.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.thomun.presentation.home.HomeScreen
import com.example.thomun.presentation.notification.NotificationScreen
import com.example.thomun.presentation.search.SearchScreen
import com.example.thomun.presentation.splash.SplashScreen

@Composable
internal fun ThomunNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.padding(innerPadding)
    ) {
        composable("splash") {
            SplashScreen(onAnimationFinished = {
                navController.navigate("home") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }
        composable("home") {
            HomeScreen(
                onSearchClick = {
                    navController.navigate("search")
                },
                onNotificationsClick = {
                    navController.navigate("notifications")
                }
            )
        }
        composable("search") {
            SearchScreen()
        }
        composable("notifications") {
            NotificationScreen()
        }
    }
}