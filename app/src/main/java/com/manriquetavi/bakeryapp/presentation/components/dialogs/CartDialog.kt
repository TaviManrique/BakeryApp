package com.manriquetavi.bakeryapp.presentation.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.manriquetavi.bakeryapp.R

@Composable
fun CartDialog(
    onDismiss: () -> Unit,
    onNegativeClick: () -> Unit,
    onPositiveClick: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(color = MaterialTheme.colors.primary)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(100.dp)
                            .graphicsLayer(
                                scaleX = 1.2f,
                                scaleY = 1.2f
                            )
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_check_solid),
                        contentDescription = "Icon"
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                   CompositionLocalProvider(
                       LocalContentAlpha provides ContentAlpha.medium
                   ) {
                       Text(
                           text = "To send a nearby place or your location, allow access to your location"
                       )
                   }
                   Row(
                       modifier = Modifier.fillMaxWidth(),
                       horizontalArrangement = Arrangement.End
                   ) {
                       Text(
                           modifier = Modifier
                               .clickable(
                                   interactionSource = MutableInteractionSource(),
                                   indication = rememberRipple(color = Color.Blue),
                                   onClick = onPositiveClick
                               )
                               .padding(8.dp),
                           text = "NOT NOW",
                           color = Color.Blue
                       )
                       Spacer(modifier = Modifier.width(4.dp))
                       Text(
                           modifier = Modifier
                               .clickable(
                                   //interactionSource = MutableInteractionSource(),
                                   //indication = rememberRipple(color = Color.Blue),
                                   onClick = onNegativeClick
                               )
                               .padding(8.dp),
                           text = "CONTINUE",
                           color = Color.Red
                       )
                   }
                }
            }
        }
    }
}

@Composable
fun ShowCartAlertDialog() {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                showDialog = !showDialog
            }
        ) {
            Text(
                text = "Open dialog"
            )
        }
        if (showDialog) {
            CartDialog(
                onDismiss = { showDialog = !showDialog },
                onNegativeClick = { showDialog = !showDialog },
                onPositiveClick = { showDialog = !showDialog }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CartDialogPreview() {
    ShowCartAlertDialog( )
}