package com.manriquetavi.bakeryapp.presentation.screens.profile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.model.User
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.presentation.components.ProgressBarCircular
import com.manriquetavi.bakeryapp.util.Util
import com.manriquetavi.bakeryapp.R
import com.manriquetavi.bakeryapp.util.ToastMessage

@Composable
fun ProfileScreen(
    screenNavController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    val signOutState = profileViewModel.signOutState.value
    val userDetails = profileViewModel.responseUserDetails.value


    Scaffold(
        topBar = {}
    ) {
        when(userDetails) {
            is Response.Loading -> ProgressBarCircular()
            is Response.Success ->
                if (userDetails.data == null) {
                    ProgressBarCircular()
                } else {
                    ProfileContent(userDetails = userDetails.data, profileViewModel = profileViewModel)
                }
            is Response.Error -> Util.printError(userDetails.message)
        }
        when(signOutState) {
            is Response.Loading -> ProgressBarCircular()
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

}

@Composable
fun ProfileContentTest(
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
                    profileViewModel.saveImageProfile(imageProfile = "")
                    profileViewModel.signOut()
                }
            ) {
                Text("SIGN OUT")
            }
        }
    }
}

@Composable
fun ProfileContent(
    userDetails: User?,
    profileViewModel: ProfileViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        //Title
        Text(
            text = "My Profile",
            maxLines = 1,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold
        )
        ProfileImage(
            profileViewModel = profileViewModel
        )
        Text(
            text = userDetails?.username.toString(),
            maxLines = 2,
            fontSize = MaterialTheme.typography.body1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .align(Alignment.Start),
            text = "Payment method"
        )
        Button(
            onClick = {
                profileViewModel.saveImageProfile(imageProfile = "")
                profileViewModel.signOut()
            }
        ) {
            Text("SIGN OUT")
        }

    }

}

@Composable
fun ProfileImage(
    profileViewModel: ProfileViewModel
) {
    //val imageUri = rememberSaveable{ mutableStateOf("") }
    val context = LocalContext.current
    val imageUri = profileViewModel.imageProfileUri.collectAsState().value
    //val painter = rememberAsyncImagePainter(imageUri.ifEmpty { R.drawable.ic_placeholder })
    val painter = rememberAsyncImagePainter(
        imageUri.ifEmpty {
            R.drawable.ic_placeholder
        }
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { it ->
        //it.let { imageUri = it.toString() }
        //it.let { profileViewModel.saveImageProfile(it.toString())
        if (it == null) return@rememberLauncherForActivityResult

        val input = context.contentResolver.openInputStream(it) ?: return@rememberLauncherForActivityResult
        val outputFile = context.filesDir.resolve("profilePic.jpg")
        input.copyTo(outputFile.outputStream())
        profileViewModel.saveImageProfile(imageProfile = outputFile.toUri().toString())
    }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp),
        shape = CircleShape
    ){
        Image(
            modifier = Modifier
                .wrapContentSize()
                .clickable { launcher.launch("image/*") },
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun ProfileContentPreview() {
    ProfileContent(
        userDetails = null,
        profileViewModel = hiltViewModel()
    )
}
