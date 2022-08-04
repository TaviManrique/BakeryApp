package com.manriquetavi.bakeryapp.presentation.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.domain.model.Food
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.presentation.common.FoodItem
import com.manriquetavi.bakeryapp.presentation.components.ProgressBarCircular
import com.manriquetavi.bakeryapp.presentation.components.SearchCakeInputField
import com.manriquetavi.bakeryapp.ui.theme.SMALL_PADDING
import com.manriquetavi.bakeryapp.util.Util

@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    screenNavController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { SearchTopBar(screenNavController = screenNavController) },
        backgroundColor = Color.Transparent
    ) { paddingValues ->
        SearchActionComponent(
            screenNavController = screenNavController,
            paddingValues = paddingValues,
            searchViewModel = searchViewModel
        )
    }
}

@Composable
fun SearchActionComponent(
    screenNavController: NavHostController,
    paddingValues: PaddingValues,
    searchViewModel: SearchViewModel
) {
    val focusManager = LocalFocusManager.current
    val searchQuery = searchViewModel.searchQuery
    val searchedFoods = searchViewModel.searchedFoods.value

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
    ) {
        SearchCakeInputField(
            modifier = Modifier.padding(top = 16.dp),
            text = searchQuery,
            onTextChange = { searchViewModel.updateSearchQuery(query = it) },
            onSearchClicked = {
                searchViewModel.searchFoods(query = it)
                              },
            onCloseClicked = { screenNavController.popBackStack() },
            focusManager = focusManager
        )
        when(searchedFoods) {
            is Response.Loading -> ProgressBarCircular()
            is Response.Success -> ListFoodFound(foods = searchedFoods.data, screenNavController = screenNavController)
            is Response.Error -> Util.printError(searchedFoods.message)
        }
    }

}

@Composable
fun ListFoodFound(
    foods: List<Food>?,
    screenNavController: NavHostController
) {
    LazyColumn(
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {
        foods?.let {
            items(
                items = foods,
                key = { food ->
                    food.id!!
                }
            ) { food ->
                FoodItem(food = food, screenNavController = screenNavController)
            }
        }
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(screenNavController = rememberNavController())
}

