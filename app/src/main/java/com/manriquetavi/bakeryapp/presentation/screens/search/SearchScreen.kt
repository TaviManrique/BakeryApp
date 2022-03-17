package com.manriquetavi.bakeryapp.presentation.screens.search

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.presentation.components.SearchCakeInputField

@Composable
fun SearchScreen(
    screenNavController: NavHostController
) {
    Scaffold(
        topBar = { SearchTopBar(screenNavController) },
        backgroundColor = Color.Transparent
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Content()
    }
}

@Composable
fun Content() {
    SearchCakeInputField(
        text = "",
        onTextChange = { mutableStateOf("") },
        onSearchClicked = {  },
        onCloseClicked = {  }
    )
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(screenNavController = rememberNavController())
}

