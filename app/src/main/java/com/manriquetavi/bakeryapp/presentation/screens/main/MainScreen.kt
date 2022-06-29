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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.manriquetavi.bakeryapp.presentation.screens.cart.CartScreen
import com.manriquetavi.bakeryapp.presentation.screens.home.HomeScreen
import com.manriquetavi.bakeryapp.presentation.screens.order.OrderScreen
import com.manriquetavi.bakeryapp.presentation.screens.profile.ProfileScreen
import com.manriquetavi.bakeryapp.ui.theme.ShimmerMediumGray

@ExperimentalCoilApi
@Composable
fun MainScreen(
    screenNavController: NavHostController
) {

    val selectedItem = remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomBar(selectedItem) {selectedItem.value = it}
        }
    ) {
        when (selectedItem.value){
            0 -> HomeScreen(screenNavController)
            1 -> CartScreen(screenNavController)
            2 -> OrderScreen(screenNavController)
            3 -> ProfileScreen(screenNavController)
        }
    }
}

@Composable
fun BottomBar(
    selectedItem: MutableState<Int>,
    onSelectedItem: (Int) -> Unit,
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

        BottomNavigationItem(
            label = { Text(text = "Home") },
            selected = selectedItem.value == 0,
            onClick = {
                if (selectedItem.value != 0) {
                    onSelectedItem(0)
                }
            },
            icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "Person Icon") },
            selectedContentColor = MaterialTheme.colors.primaryVariant,
            unselectedContentColor = Color.LightGray,
            enabled = true,
            alwaysShowLabel = false
        )
        BottomNavigationItem(
            label = { Text(text = "Cart") },
            selected = selectedItem.value == 1,
            onClick = {
                if (selectedItem.value != 1) {
                    onSelectedItem(1)
                }
            },
            icon = { Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Phone Icon") },
            selectedContentColor = MaterialTheme.colors.primaryVariant,
            unselectedContentColor = Color.LightGray,
            enabled = true,
            alwaysShowLabel = false
        )
        BottomNavigationItem(
            label = { Text(text = "Order") },
            selected = selectedItem.value == 2,
            onClick = {
                if (selectedItem.value != 2) {
                    onSelectedItem(2)
                }
            },
            icon = { Icon(imageVector = Icons.Filled.List, contentDescription = "Place Icon") },
            selectedContentColor = MaterialTheme.colors.primaryVariant,
            unselectedContentColor = Color.LightGray,
            enabled = true,
            alwaysShowLabel = false
        )
        BottomNavigationItem(
            label = { Text(text = "Profile") },
            selected = selectedItem.value == 3,
            onClick = {
                if (selectedItem.value != 3) {
                    onSelectedItem(3)
                }
            },
            icon = { Icon(imageVector = Icons.Filled.Person, contentDescription = "Place Icon") },
            selectedContentColor = MaterialTheme.colors.primaryVariant,
            unselectedContentColor = Color.LightGray,
            enabled = true,
            alwaysShowLabel = false
        )
    }
}


/*
@Composable
fun BottomBar(
    selectedItem: MutableState<Int>,
    navController: NavHostController,
    onSelectedItem: (Int) -> Unit,
) {
    val screens = listOf(
        Screen.Home,
        Screen.Cart,
        Screen.Order,
        Screen.Profile,
    )
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    BottomNavigation(
        modifier = Modifier
            .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(30)),
        backgroundColor = ShimmerMediumGray,
        elevation = 10.dp
    ) {
        screens.forEach{ screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                bottomNavController = bottomNavController
            )
        }
    }
}*/


/*
@Composable
fun RowScope.AddItem(
    screen: Screen,
    currentDestination: NavDestination?,
    bottomNavController: NavHostController
) {
    BottomNavigationItem(
        label = { Text(text = screen.title.toString()) },
        icon = {
               Icon(
                   imageVector = screen.icon!!,
                   contentDescription = "Navigation Icon"
               )
        },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            bottomNavController.navigate(screen.route) {
                popUpTo(bottomNavController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        selectedContentColor = MaterialTheme.colors.primaryVariant,
        unselectedContentColor = Color.LightGray,
        alwaysShowLabel = false
    )
}*/


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