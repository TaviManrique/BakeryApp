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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.presentation.components.ProgressBar
import com.manriquetavi.bakeryapp.util.Util

@Composable
fun ProfileScreen(
    screenNavController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    val activity = (LocalContext.current as? Activity)
    val signOutState = profileViewModel.signOutState.value
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
                    profileViewModel.signOut()
                }
            ) {
                Text("SIGN OUT")
            }
        }
    }

    when(signOutState) {
        is Response.Loading -> ProgressBar()
        is Response.Success ->
            if(signOutState.data) {
                LaunchedEffect(signOutState.data) {
                    screenNavController.popBackStack()
                    screenNavController.navigate(Screen.Login.route)
                }
            }
        is Response.Error -> LaunchedEffect(Unit) {
            Util.printError(signOutState.message)
        }
    }

}