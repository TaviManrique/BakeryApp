package com.manriquetavi.bakeryapp.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.manriquetavi.bakeryapp.presentation.screens.cart.CartScreen
import com.manriquetavi.bakeryapp.presentation.screens.checkout.CheckoutScreen
import com.manriquetavi.bakeryapp.presentation.screens.details.DetailsScreen
import com.manriquetavi.bakeryapp.presentation.screens.home.HomeScreen
import com.manriquetavi.bakeryapp.presentation.screens.location.LocationScreen
import com.manriquetavi.bakeryapp.presentation.screens.login.LoginScreen
import com.manriquetavi.bakeryapp.presentation.screens.main.MainScreen
import com.manriquetavi.bakeryapp.presentation.screens.order.OrderScreen
import com.manriquetavi.bakeryapp.presentation.screens.profile.ProfileScreen
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
            route = Graph.MAIN
        ) {
            MainScreen(screenNavController = screenNavController)
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalCoilApi
@Composable
fun MainNavGraph(navController: NavHostController, paddingValues: PaddingValues, screenNavController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController = navController, paddingValues = paddingValues)
        }
        composable(route = BottomBarScreen.Cart.route) {
            CartScreen(navController = navController, paddingValues = paddingValues)
        }
        composable(route = BottomBarScreen.Order.route) {
            OrderScreen(navController = navController, paddingValues = paddingValues)
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(navController = navController, screenNavController = screenNavController, paddingValues = paddingValues)
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
            SearchScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("foodId") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailsScreen(navController = navController)
        }
        composable(route = Screen.Location.route) {
            LocationScreen(navController = navController)
        }
        composable(route = Screen.Checkout.route) {
            CheckoutScreen(navController = navController)
        }
        composable(route = Screen.Track.route) {
            TrackScreen(navController = navController)
        }
    }
}

object Graph {
    const val MAIN = "main_graph"
}