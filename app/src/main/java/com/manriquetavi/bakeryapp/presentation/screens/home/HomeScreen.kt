package com.manriquetavi.bakeryapp.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.manriquetavi.bakeryapp.presentation.components.*

@ExperimentalCoilApi
@Composable
fun HomeScreen(
    screenNavController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { HomeTopBar(screenNavController) },
        backgroundColor = Color.Transparent,
    ) {
        HomeContent(screenNavController = screenNavController, homeViewModel = homeViewModel)
    }
}

@ExperimentalCoilApi
@Composable
fun HomeContent(
    homeViewModel: HomeViewModel,
    screenNavController: NavHostController
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        PromotionsLazyRow(homeViewModel)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Category", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        CategoriesLazyRow(homeViewModel, screenNavController)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Recommendation", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        RecommendationsLazyRow(homeViewModel, screenNavController)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(screenNavController = rememberNavController())
}