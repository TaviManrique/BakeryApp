package com.manriquetavi.bakeryapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.manriquetavi.bakeryapp.presentation.screens.cart.CartScreen
import com.manriquetavi.bakeryapp.presentation.screens.home.HomeScreen
import com.manriquetavi.bakeryapp.presentation.screens.order.OrderScreen
import com.manriquetavi.bakeryapp.presentation.screens.profile.ProfileScreen


@Composable
fun BottomNavGraph(bottomNavController: NavHostController, screenNavController: NavHostController) {
    NavHost(
        navController = bottomNavController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(screenNavController)
        }
        composable(route = Screen.Cart.route) {
            CartScreen()
        }
        composable(route = Screen.Order.route) {
            OrderScreen()
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen()
        }
    }
}