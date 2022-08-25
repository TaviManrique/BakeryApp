package com.manriquetavi.bakeryapp.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
    navController: NavHostController,
    paddingValues: PaddingValues,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        HomeTopBar(navController = navController)
        HomeScreenContent(
            navController = navController,
            homeViewModel = homeViewModel
        )
    }
}

@ExperimentalCoilApi
@Composable
fun HomeScreenContent(
    homeViewModel: HomeViewModel,
    navController: NavHostController
) {
    PromotionsLazyRow(homeViewModel)
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = "Category", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(16.dp))
    CategoriesLazyRow(homeViewModel, navController)
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = "Recommendation", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(16.dp))
    RecommendationsLazyRow(homeViewModel, navController)
    Spacer(modifier = Modifier.height(16.dp))

}

@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController(), paddingValues = PaddingValues())
}