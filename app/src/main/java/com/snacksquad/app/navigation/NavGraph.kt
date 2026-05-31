package com.snacksquad.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Auth : Screen("auth")
    object Home : Screen("home")
    object Search : Screen("search")
    object ProductDetail : Screen("product_detail/{productId}") {
        fun createRoute(productId: String) = "product_detail/$productId"
    }
    object Cart : Screen("cart")
    object Checkout : Screen("checkout")
    object OrderConfirmation : Screen("order_confirmation")
    object Profile : Screen("profile")
}

@Composable
fun SnackSquadNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Splash.route) {
            // Splash Screen placeholder
        }
        composable(route = Screen.Onboarding.route) {
            // Onboarding placeholder
        }
        composable(route = Screen.Auth.route) {
            // Auth placeholder
        }
        composable(route = Screen.Home.route) {
            // Home placeholder
        }
        // Additional screens will be added here
    }
}
