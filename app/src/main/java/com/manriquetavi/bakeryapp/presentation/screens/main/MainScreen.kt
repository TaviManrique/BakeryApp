package com.manriquetavi.bakeryapp.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.navigation.BottomNavGraph
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.ui.theme.ShimmerDarkGray
import com.manriquetavi.bakeryapp.ui.theme.ShimmerMediumGray

@Composable
fun MainScreen(navController: NavHostController) {
    val bottomNavController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = bottomNavController) }
    ) {
        BottomNavGraph(navController = bottomNavController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        Screen.Home,
        Screen.Cart,
        Screen.Order,
        Screen.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
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
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Screen,
    currentDestination: NavDestination?,
    navController: NavHostController
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
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        selectedContentColor = MaterialTheme.colors.primaryVariant,
        unselectedContentColor = Color.LightGray,
        alwaysShowLabel = false
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    BottomBar(navController = rememberNavController())
}