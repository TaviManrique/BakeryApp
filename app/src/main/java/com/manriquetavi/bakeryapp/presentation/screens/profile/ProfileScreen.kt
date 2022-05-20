package com.manriquetavi.bakeryapp.presentation.screens.profile

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.presentation.AuthenticationViewModel
import com.manriquetavi.bakeryapp.presentation.components.ProgressBar
import com.manriquetavi.bakeryapp.util.Util

@Composable
fun ProfileScreen(
    screenNavController: NavHostController,
    bottomNavController: NavHostController,
    authenticationViewModel: AuthenticationViewModel = hiltViewModel(),
) {
    val activity = (LocalContext.current as? Activity)
    val signOutState = authenticationViewModel.signOutState.value
    val user = FirebaseAuth.getInstance().currentUser
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Row() {
                Text(
                    text = "uid: "
                )
                Text(
                    text = user?.uid.toString()
                )
            }
            Row() {
                Text(
                    text = "name: "
                )
                Text(
                    text = user?.displayName.toString()
                )
            }

            Row() {
                Text(
                    text = "email: "
                )
                Text(
                    text = user?.email.toString()
                )
            }
            Button(
                onClick = {
                    authenticationViewModel.signOut()
                }
            ) {
                Text("SIGN OUT")
            }
        }
    }

    when(signOutState) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> if(signOutState.data) {
            //Show screen final session
            LaunchedEffect(signOutState.data) {
                activity?.finish()
            }
        }
        is Response.Error -> LaunchedEffect(Unit) {
            Util.printError(signOutState.message)
        }
    }

}