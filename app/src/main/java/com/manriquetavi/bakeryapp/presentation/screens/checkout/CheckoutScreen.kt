package com.manriquetavi.bakeryapp.presentation.screens.checkout

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun CheckoutScreen(
    screenNavController: NavHostController
) {

    Scaffold(
        topBar = { CheckoutTopBar(screenNavController = screenNavController) }
    ) {

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
    }
}


@Preview(showSystemUi = true)
@Composable
fun CheckoutScreenPreview() {
    CheckoutScreen(screenNavController = rememberNavController())
}