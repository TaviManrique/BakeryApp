package com.manriquetavi.bakeryapp.presentation.screens.search

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.presentation.components.SearchCakeInputField

@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    screenNavController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { SearchTopBar(screenNavController) },
        backgroundColor = Color.Transparent
    ) {
        Content(
            screenNavController = screenNavController,
            searchViewModel = searchViewModel
        )
    }
}

@Composable
fun Content(
    screenNavController: NavHostController,
    searchViewModel: SearchViewModel
) {
    val focusManager = LocalFocusManager.current
    //val text = rememberSaveable { mutableStateOf("") }
    val searchQuery = searchViewModel.searchQuery
    val searchedFoods by searchViewModel.searchedFoods

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        SearchCakeInputField(
            text = searchQuery,
            onTextChange = { searchViewModel.updateSearchQuery(query = it) },
            onSearchClicked = {
                searchViewModel.searchFoods(query = it)
                Log.d("SearchScreen", "searchedFoods: $searchedFoods")
                              },
            onCloseClicked = { screenNavController.popBackStack() },
            focusManager = focusManager
        )
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(screenNavController = rememberNavController())
}

