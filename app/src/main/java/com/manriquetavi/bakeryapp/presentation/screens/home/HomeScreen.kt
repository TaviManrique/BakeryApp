package com.manriquetavi.bakeryapp.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.manriquetavi.bakeryapp.presentation.components.CategoriesLazyRow
import com.manriquetavi.bakeryapp.presentation.components.PromotionItem
import com.manriquetavi.bakeryapp.presentation.components.PromotionsLazyRow
import com.manriquetavi.bakeryapp.presentation.components.RecommendationsLazyRow

@Composable
fun HomeScreen(
) {
    Scaffold(
        topBar = { HomeTopBar() },
        backgroundColor = Color.Transparent,
    ) {
        Content()
    }
}

@Composable
fun Content() {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        PromotionsLazyRow()
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Category", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))
        CategoriesLazyRow()
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Recommendation", style = MaterialTheme.typography.h6)
        RecommendationsLazyRow()
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}