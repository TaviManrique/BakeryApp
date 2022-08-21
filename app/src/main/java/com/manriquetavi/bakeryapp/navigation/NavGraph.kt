package com.manriquetavi.bakeryapp.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.manriquetavi.bakeryapp.presentation.screens.checkout.CheckoutScreen
import com.manriquetavi.bakeryapp.presentation.screens.details.DetailsScreen
import com.manriquetavi.bakeryapp.presentation.screens.location.LocationScreen
import com.manriquetavi.bakeryapp.presentation.screens.login.LoginScreen
import com.manriquetavi.bakeryapp.presentation.screens.main.MainScreen
import com.manriquetavi.bakeryapp.presentation.screens.register.RegisterScreen
import com.manriquetavi.bakeryapp.presentation.screens.search.SearchScreen
import com.manriquetavi.bakeryapp.presentation.screens.splash.SplashScreen
import com.manriquetavi.bakeryapp.presentation.screens.track.TrackScreen
import com.manriquetavi.bakeryapp.presentation.screens.welcome.WelcomeScreen

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
fun SetupNavGraph(screenNavController: NavHostController) {
    NavHost(
        navController = screenNavController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(screenNavController = screenNavController)
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(screenNavController = screenNavController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(screenNavController = screenNavController)
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(screenNavController = screenNavController)
        }
        composable(
            route = Screen.Main.route,
            arguments = listOf(
                navArgument("itemPosition") {
                    type = NavType.StringType
                    defaultValue = null
                    nullable = true
                }
            )
        ) {
            MainScreen(screenNavController = screenNavController)
        }
        composable(
            route = Screen.Search.route,
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                    defaultValue = null
                    nullable = true
                }
            )
        ) {
            SearchScreen(screenNavController = screenNavController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("foodId") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailsScreen(screenNavController = screenNavController)
        }
        composable(route = Screen.Location.route) {
            LocationScreen(screenNavController = screenNavController)
        }
        composable(route = Screen.Checkout.route) {
            CheckoutScreen(screenNavController = screenNavController)
        }

        composable(route = Screen.Track.route) {
            TrackScreen(screenNavController = screenNavController)
        }

    }
}