package com.manriquetavi.bakeryapp.presentation.screens.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.model.User
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.presentation.components.ProgressBar
import com.manriquetavi.bakeryapp.util.ToastMessage
import com.manriquetavi.bakeryapp.util.Util

@Composable
fun ProfileScreen(
    screenNavController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    val signOutState = profileViewModel.signOutState.value
    val userDetails = profileViewModel.responseUserDetails.value


    Scaffold(
    ) {
        when(userDetails) {
            is Response.Loading -> ProgressBar()
            is Response.Success ->
                if (userDetails.data == null) {
                    ProgressBar()
                } else {
                    ProfileContent(userDetails = userDetails.data, profileViewModel = profileViewModel)
                }
            is Response.Error -> Util.printError(userDetails.message)
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

@Composable
fun ProfileContent(
    userDetails: User?,
    profileViewModel: ProfileViewModel
) {
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
                    text = userDetails?.uid ?: " "
                )
            }
            Row() {
                Text(
                    text = "name: "
                )
                Text(
                    text = userDetails?.username ?: " "
                )
            }

            Row() {
                Text(
                    text = "email: "
                )
                Text(
                    text = userDetails?.email ?: " "
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
}
