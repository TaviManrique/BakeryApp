package com.manriquetavi.bakeryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.navigation.SetupNavGraph
import com.manriquetavi.bakeryapp.ui.theme.BakeryAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var screenNavController: NavHostController
    private lateinit var bottomNavController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BakeryAppTheme {
                // A surface container using the 'background' color from the theme
                screenNavController =  rememberNavController()
                bottomNavController = rememberNavController()
                SetupNavGraph(screenNavController = screenNavController, bottomNavController = bottomNavController)
            }
        }
    }
}