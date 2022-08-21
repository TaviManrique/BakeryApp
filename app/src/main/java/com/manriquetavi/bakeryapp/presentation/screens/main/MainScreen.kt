package com.manriquetavi.bakeryapp.presentation.screens.main

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.navigation.bottomScreens
import com.manriquetavi.bakeryapp.presentation.screens.cart.CartScreen
import com.manriquetavi.bakeryapp.presentation.screens.home.HomeScreen
import com.manriquetavi.bakeryapp.presentation.screens.order.OrderScreen
import com.manriquetavi.bakeryapp.presentation.screens.profile.ProfileScreen
import com.manriquetavi.bakeryapp.ui.theme.ShimmerMediumGray

@ExperimentalCoilApi
@Composable
fun MainScreen(
    screenNavController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val selectedItem = mainViewModel.selectedItem

    Scaffold(
        bottomBar = {
            BottomBar(selectedItem) { mainViewModel.updateSelectedItem(it) }
        }
    ) { paddingValues ->
        when (selectedItem.value){
            0 -> HomeScreen(screenNavController, paddingValues)
            1 -> CartScreen(screenNavController, paddingValues)
            2 -> OrderScreen(screenNavController, paddingValues)
            3 -> ProfileScreen(screenNavController, paddingValues)
        }
    }
}

@Composable
fun BottomBar(
    selectedItem: State<Int>,
    onSelectedItem: (Int) -> Unit
) {
    BottomNavigation(
        modifier = Modifier
            .padding(start = 16.dp, bottom = 8.dp, end = 16.dp)
            .clip(RoundedCornerShape(30)),
        backgroundColor = ShimmerMediumGray,
        elevation = 10.dp
    ) {
        val activity = (LocalContext.current as? Activity)
        BackHandler {
            if (selectedItem.value == 0) {
                activity?.finish()
            } else {
                onSelectedItem(0)
            }
        }

        bottomScreens.forEach { screen ->
            BottomNavigationItem(
                label = { Text(text = screen.title.toString()) },
                selected = selectedItem.value == bottomScreens.indexOf(screen),
                onClick = {
                    if (selectedItem.value != bottomScreens.indexOf(screen)) {
                        onSelectedItem(bottomScreens.indexOf(screen))
                    }
                },
                icon = { screen.icon?.let { Icon(imageVector = it, contentDescription = "Bottom Nav Icon") } },
                selectedContentColor = MaterialTheme.colors.primaryVariant,
                unselectedContentColor = Color.LightGray,
                enabled = true,
                alwaysShowLabel = false
            )
        }
    }
}

@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(screenNavController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBar(
        selectedItem = remember { mutableStateOf(1) },
        onSelectedItem = {  }
    )
}