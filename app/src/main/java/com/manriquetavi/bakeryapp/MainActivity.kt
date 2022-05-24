package com.manriquetavi.bakeryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.manriquetavi.bakeryapp.navigation.SetupNavGraph
import com.manriquetavi.bakeryapp.ui.theme.BakeryAppTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var screenNavController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BakeryAppTheme {
                // A surface container using the 'background' color from the theme
                screenNavController =  rememberNavController()
                SetupNavGraph(screenNavController = screenNavController)
            }
        }
    }
}