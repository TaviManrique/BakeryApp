package com.manriquetavi.bakeryapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.manriquetavi.bakeryapp.presentation.screens.cart.CartScreen
import com.manriquetavi.bakeryapp.presentation.screens.home.HomeScreen
import com.manriquetavi.bakeryapp.presentation.screens.main.MainScreen
import com.manriquetavi.bakeryapp.presentation.screens.order.OrderScreen
import com.manriquetavi.bakeryapp.presentation.screens.profile.ProfileScreen
import com.manriquetavi.bakeryapp.presentation.screens.search.SearchScreen
import com.manriquetavi.bakeryapp.presentation.screens.splash.SplashScreen
import com.manriquetavi.bakeryapp.presentation.screens.welcome.WelcomeScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
        route = "root"
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Main.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen()
        }
    }
}