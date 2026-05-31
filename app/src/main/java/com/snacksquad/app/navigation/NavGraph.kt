package com.snacksquad.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.snacksquad.app.data.repository.MockLlmAssistantRepository
import com.snacksquad.app.data.repository.MockProductRepository
import com.snacksquad.app.ui.screens.auth.AuthScreen
import com.snacksquad.app.ui.screens.cart.CartScreen
import com.snacksquad.app.ui.screens.checkout.CheckoutScreen
import com.snacksquad.app.ui.screens.checkout.OrderConfirmationScreen
import com.snacksquad.app.ui.screens.home.HomeScreen
import com.snacksquad.app.ui.screens.home.LlmAssistScreen
import com.snacksquad.app.ui.screens.onboarding.OnboardingScreen
import com.snacksquad.app.ui.screens.product.ProductDetailScreen
import com.snacksquad.app.ui.screens.profile.ProfileScreen
import com.snacksquad.app.ui.screens.splash.SplashScreen
import com.snacksquad.app.ui.screens.tracking.OrderTrackingScreen
import com.snacksquad.app.domain.models.User
import java.util.UUID

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Auth : Screen("auth")
    object Home : Screen("home")
    object Search : Screen("search")
    object ProductDetail : Screen("product_detail")
    object Cart : Screen("cart")
    object Checkout : Screen("checkout")
    object OrderConfirmation : Screen("order_confirmation")
    object OrderTracking : Screen("order_tracking")
    object Profile : Screen("profile")
    object LlmAssist : Screen("llm_assist")
}

@Composable
fun SnackSquadNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route
) {
    val productRepo = remember { MockProductRepository() }
    val llmRepo = remember { MockLlmAssistantRepository() }
    
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(onSplashComplete = {
                navController.navigate(Screen.Onboarding.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }
        composable(route = Screen.Onboarding.route) {
            OnboardingScreen(onFinishOnboarding = {
                navController.navigate(Screen.Auth.route) {
                    popUpTo(Screen.Onboarding.route) { inclusive = true }
                }
            })
        }
        composable(route = Screen.Auth.route) {
            AuthScreen(onAuthSuccess = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Auth.route) { inclusive = true }
                }
            })
        }
        composable(route = Screen.Home.route) {
            HomeScreen(
                categories = productRepo.getCategories(),
                products = productRepo.getFeaturedProducts(),
                onProductClick = { navController.navigate(Screen.ProductDetail.route) },
                onCartClick = { navController.navigate(Screen.Cart.route) },
                onLlmAssistClick = { navController.navigate(Screen.LlmAssist.route) }
            )
        }
        composable(route = Screen.LlmAssist.route) {
            LlmAssistScreen(
                onBackClick = { navController.popBackStack() },
                onProductClick = { navController.navigate(Screen.ProductDetail.route) }
            )
        }
        composable(route = Screen.ProductDetail.route) {
            ProductDetailScreen(
                product = productRepo.getFeaturedProducts().first(),
                onBackClick = { navController.popBackStack() },
                onAddToCart = { _, _, _ -> navController.navigate(Screen.Cart.route) }
            )
        }
        composable(route = Screen.Cart.route) {
            CartScreen(
                cartItems = emptyList(), // Pass mock items if desired
                onBackClick = { navController.popBackStack() },
                onCheckoutClick = { navController.navigate(Screen.Checkout.route) },
                onRemoveItem = {}
            )
        }
        composable(route = Screen.Checkout.route) {
            CheckoutScreen(
                onBackClick = { navController.popBackStack() },
                onPlaceOrder = { navController.navigate(Screen.OrderConfirmation.route) }
            )
        }
        composable(route = Screen.OrderConfirmation.route) {
            OrderConfirmationScreen(
                orderId = UUID.randomUUID().toString().substring(0, 8),
                onTrackOrderClick = { navController.navigate(Screen.OrderTracking.route) },
                onContinueShoppingClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }
        composable(route = Screen.OrderTracking.route) {
            OrderTrackingScreen(
                orderId = "123",
                onBackClick = { navController.navigate(Screen.Home.route) { popUpTo(Screen.Home.route) { inclusive = true } } }
            )
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(
                user = User("1", "Leo", "leo@example.com"),
                onNavigateToOrderHistory = { },
                onLogoutClick = {
                    navController.navigate(Screen.Auth.route) {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}
