package com.manriquetavi.bakeryapp.presentation.screens.splash

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.R
import com.manriquetavi.bakeryapp.navigation.Graph
import com.manriquetavi.bakeryapp.util.ToastMessage

@Composable
fun SplashScreen(
    screenNavController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel(),
) {
    val onBoardingPageCompleted by splashViewModel.onBoardingCompleted.collectAsState()
    val isUserAuthenticated = splashViewModel.isUserAuthenticated
    val degrees = remember { Animatable(0f) }
    
    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1500,
                delayMillis = 200
            )
        )
        screenNavController.popBackStack()
        if(onBoardingPageCompleted) {
            Log.d("Splash Screen", "OnBoardingPage: Completed")
            if(isUserAuthenticated) {
                screenNavController.navigate(Graph.MAIN)
            }
            else {
                screenNavController.navigate(Screen.Login.route)
            }
        }
        else {
            screenNavController.navigate(Screen.Welcome.route)
        }
    }
    Splash(degrees = degrees.value)
}

@Composable
fun Splash(
    degrees: Float
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.rotate(degrees = degrees),
            painter = painterResource(id = R.drawable.logo_bakery),
            contentDescription = "App Logo"
        )
    }
}