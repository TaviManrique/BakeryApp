package com.manriquetavi.bakeryapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(
    val route: String
) {
    //Screens
    object Splash: Screen("splash_screen")
    object Welcome: Screen("welcome_screen")
    object Login: Screen("login_screen")
    object Register: Screen("register_screen")
    object Main: Screen("main_screen")
    object Search: Screen("search_screen?category={category}") {
        fun passCategory(category: String? = null): String {
            return "search_screen?category=$category"
        }
    }
    object Details: Screen("details_screen/{foodId}") {
        fun passFoodId(foodId: String): String {
            return "details_screen/$foodId"
        }
    }
    object Location: Screen("location_screen")
    object Checkout: Screen("checkout_screen")
    object Track: Screen("track_screen/{orderId}") {
        fun passOrderId(orderId: String): String {
            return "track_screen/$orderId"
        }
    }
}

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    //BottomNav Screens
    object Home: BottomBarScreen(
        route = "home_screen",
        title = "Home",
        icon = Icons.Outlined.Home
    )
    object Order: BottomBarScreen(
        route = "order_screen",
        title = "Order",
        icon = Icons.Outlined.List
    )
    object Cart: BottomBarScreen(
        route = "cart_screen",
        title = "Cart",
        icon = Icons.Outlined.ShoppingCart
    )
    object Profile: BottomBarScreen(
        route = "profile_screen",
        title = "Profile",
        icon = Icons.Outlined.Person
    )
}


val bottomScreens = listOf(
    BottomBarScreen.Home,
    BottomBarScreen.Cart,
    BottomBarScreen.Order,
    BottomBarScreen.Profile,
)

