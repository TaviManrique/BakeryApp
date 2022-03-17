package com.manriquetavi.bakeryapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.manriquetavi.bakeryapp.presentation.screens.main.MainScreen
import com.manriquetavi.bakeryapp.presentation.screens.search.SearchScreen
import com.manriquetavi.bakeryapp.presentation.screens.splash.SplashScreen
import com.manriquetavi.bakeryapp.presentation.screens.welcome.WelcomeScreen

@ExperimentalComposeUiApi
@Composable
fun SetupNavGraph(screenNavController: NavHostController, bottomNavController: NavHostController) {
    NavHost(
        navController = screenNavController,
        startDestination = Screen.Main.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(screenNavController = screenNavController)
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(screenNavController = screenNavController)
        }
        composable(route = Screen.Main.route) {
            MainScreen(screenNavController = screenNavController, bottomNavController = bottomNavController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(screenNavController = screenNavController)
        }
    }
}