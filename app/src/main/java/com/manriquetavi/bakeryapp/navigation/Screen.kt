package com.manriquetavi.bakeryapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object Splash: Screen("splash_screen")
    object Welcome: Screen("welcome_screen")
    object Main: Screen("main_screen")
    object Home: Screen(
        route = "home_screen",
        title = "Home",
        icon = Icons.Outlined.Home
    )
    object Order: Screen(
        route = "order_screen",
        title = "Order",
        icon = Icons.Outlined.List
    )
    object Cart: Screen(
        route = "cart_screen",
        title = "Cart",
        icon = Icons.Outlined.ShoppingCart
    )
    object Profile: Screen(
        route = "profile_screen",
        title = "Profile",
        icon = Icons.Outlined.Person
    )
    object Details: Screen("details_screen/{foodId}") {
        fun passFoodId(foodId: Int): String {
            return "details_screen/$foodId"
        }
    }
}