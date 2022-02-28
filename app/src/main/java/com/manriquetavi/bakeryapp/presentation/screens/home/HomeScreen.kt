package com.manriquetavi.bakeryapp.presentation.screens.home

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
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