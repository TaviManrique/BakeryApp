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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.domain.model.Category
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.presentation.components.CategoriesLazyRow
import com.manriquetavi.bakeryapp.presentation.components.ProgressBar
import com.manriquetavi.bakeryapp.presentation.components.PromotionsLazyRow
import com.manriquetavi.bakeryapp.presentation.components.RecommendationsLazyRow
import com.manriquetavi.bakeryapp.util.Util

@Composable
fun HomeScreen(
    screenNavController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val allCategories = homeViewModel.allCategories.value

    Scaffold(
        topBar = { HomeTopBar(screenNavController) },
        backgroundColor = Color.Transparent,
    ) {
        when(allCategories) {
            is Response.Loading -> ProgressBar()
            is Response.Success -> HomeContent(categories = allCategories.data, screenNavController = screenNavController)
            is Response.Error -> Util.printError(allCategories.message)
        }
    }
}

@Composable
fun HomeContent(
    categories: List<Category>?,
    screenNavController: NavHostController
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        PromotionsLazyRow()
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Category", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))
        CategoriesLazyRow(categories, screenNavController)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Recommendation", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))
        RecommendationsLazyRow()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(screenNavController = rememberNavController())
}