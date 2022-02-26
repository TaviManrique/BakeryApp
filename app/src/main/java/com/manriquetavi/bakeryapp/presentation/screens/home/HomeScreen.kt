package com.manriquetavi.bakeryapp.presentation.screens.home

import android.content.Context
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navController: NavHostController? = null
) {
    Scaffold(
        topBar = { HomeTopBar() },
        backgroundColor = Color.Transparent,
    ) {

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}