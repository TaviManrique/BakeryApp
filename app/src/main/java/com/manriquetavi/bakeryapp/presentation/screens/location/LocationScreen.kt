package com.manriquetavi.bakeryapp.presentation.screens.location

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun LocationScreen(
    screenNavController: NavHostController
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "BOBA WEBON AQUI EMPIEZA TU PARTE",
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun LocationScreenPreview() {
    LocationScreen(screenNavController = rememberNavController())
}