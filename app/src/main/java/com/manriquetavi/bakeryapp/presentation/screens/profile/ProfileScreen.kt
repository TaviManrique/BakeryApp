package com.manriquetavi.bakeryapp.presentation.screens.profile

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.model.User
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.presentation.components.ProgressBarCircular
import com.manriquetavi.bakeryapp.util.Util
import com.manriquetavi.bakeryapp.R
import com.manriquetavi.bakeryapp.presentation.components.dialogs.AlertDialogDeleteFoodCart
import com.manriquetavi.bakeryapp.ui.theme.Purple700
import com.manriquetavi.bakeryapp.ui.theme.SMALL_PADDING
import com.manriquetavi.bakeryapp.ui.theme.descriptionColor
import com.manriquetavi.bakeryapp.ui.theme.titleColor

@Composable
fun ProfileScreen(
    screenNavController: NavHostController,
    paddingValues: PaddingValues,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val signOutState = profileViewModel.signOutState.value
    val userDetails = profileViewModel.responseUserDetails.value

    when (userDetails) {
        is Response.Loading -> ProgressBarCircular()
        is Response.Success ->
            if (userDetails.data == null) {
                ProgressBarCircular()
            } else {
                ProfileContent(
                    userDetails = userDetails.data,
                    profileViewModel = profileViewModel,
                    screenNavController = screenNavController
                )
            }
        is Response.Error -> Util.printError(userDetails.message)
    }
    when (signOutState) {
        is Response.Loading -> ProgressBarCircular()
        is Response.Success ->
            if (signOutState.data) {
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
    profileViewModel: ProfileViewModel,
    screenNavController: NavHostController
) {
    val context = LocalContext.current
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) AlertDialogDeleteFoodCart(
        title = "Bakery App",
        text = "Are you sure to log out?",
        showDialog = showDialog
    ) {
        profileViewModel.saveImageProfile(imageProfile = "")
        profileViewModel.deleteAllFoodCart()
        profileViewModel.signOut()
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        //Title
        Text(
            text = "My Profile",
            maxLines = 1,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.titleColor,
        )
        ProfileImage(
            profileViewModel = profileViewModel
        )
        Text(
            text = userDetails?.username.toString(),
            maxLines = 2,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.Start),
            text = "Address",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        ProfileItemOptions(
            icon = Icons.Filled.LocationOn,
            description = "Address",
            actionIcon = Icons.Filled.ArrowRightAlt
        ) {
            Toast.makeText(context, "Address", Toast.LENGTH_SHORT).show()
            screenNavController.navigate(Screen.Location.route)
        }
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.Start),
            text = "Settings",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        ProfileItemOptions(
            icon = Icons.Filled.Notifications,
            description = "Notification"
        ) {
            Toast.makeText(context, "Notification", Toast.LENGTH_SHORT).show()
        }
        ProfileItemOptions(
            icon = Icons.Filled.Language,
            description = "Language"
        ) {
            Toast.makeText(context, "Language", Toast.LENGTH_SHORT).show()
        }
        ProfileItemOptions(
            icon = Icons.Filled.Logout,
            description = "Log Out"
        ) {
            showDialog.value = true
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
            .padding(16.dp)
            .size(100.dp),
        shape = CircleShape,
        elevation = SMALL_PADDING
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

@Composable
fun ProfileItemOptions(
    icon: ImageVector,
    description: String,
    actionIcon: ImageVector? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 1.dp)
            .height(48.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
            .background(Purple700.copy(alpha = 0.2f))
            .clickable { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(horizontal = SMALL_PADDING)
                .size(32.dp),
            imageVector = icon,
            contentDescription = "Icon Profile Item",
            tint = MaterialTheme.colors.primary
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = description,
                fontSize = MaterialTheme.typography.body1.fontSize,
                color = MaterialTheme.colors.descriptionColor
            )
            actionIcon?.let {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = SMALL_PADDING)
                        .size(32.dp),
                    imageVector = actionIcon,
                    contentDescription = "Action Icon",
                    tint = MaterialTheme.colors.primary
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileItemOptions(
        icon = Icons.Filled.LocationOn,
        description = "Item Title"
    ) {

    }
}
