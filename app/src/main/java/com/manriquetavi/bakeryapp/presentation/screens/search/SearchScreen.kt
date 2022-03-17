package com.manriquetavi.bakeryapp.presentation.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.presentation.components.SearchCakeInputField

@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    screenNavController: NavHostController
) {
    Scaffold(
        topBar = { SearchTopBar(screenNavController) },
        backgroundColor = Color.Transparent
    ) {
        Content(screenNavController = screenNavController)
    }
}

@ExperimentalComposeUiApi
@Composable
fun Content(screenNavController: NavHostController) {
    val focusManager = LocalFocusManager.current
    val text = rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        SearchCakeInputField(
            text = text,
            //onTextChange = { mutableStateOf("") },
            onSearchClicked = {  },
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

