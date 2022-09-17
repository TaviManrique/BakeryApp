package com.manriquetavi.bakeryapp.presentation.screens.checkout

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.ui.theme.buttonBackgroundColor
import com.manriquetavi.bakeryapp.ui.theme.titleColor

@Composable
fun CheckoutTopBar(
    screenNavController: NavHostController
) {
    TopAppBar(
        title = {
            Text(
                text = "Checkout",
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.titleColor,
                textAlign = TextAlign.Center
            )

        },
        navigationIcon = {
            IconButton(onClick = { screenNavController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = MaterialTheme.colors.buttonBackgroundColor
                )
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}

@Preview(showBackground = true)
@Composable
fun CheckoutTopBarPreview() {
    CheckoutTopBar(screenNavController = rememberNavController())
}