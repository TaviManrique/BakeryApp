package com.manriquetavi.bakeryapp.presentation.components.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import com.manriquetavi.bakeryapp.domain.model.FoodCart
import com.manriquetavi.bakeryapp.presentation.screens.cart.CartViewModel

@Composable
fun AlertDialogDeleteFoodCart(
    showDialog: MutableState<Boolean>,
    title: String,
    text: String,
    confirmButtonClicked: () -> Unit
)
{
    AlertDialog(
        onDismissRequest = {
            showDialog.value = false
                           },
        title = {
            Text(
                text = title
            ) },
        text = {
            Text(
                text = text
            ) },
        confirmButton = {
            TextButton(onClick = {
                showDialog.value = false
                confirmButtonClicked.invoke()
                //foodCart.id?.let { cartViewModel.deleteFoodCart(it) }
            }) {
                Text(
                    text = "YES"
                )
            }
                        },
        dismissButton = {
            TextButton(
                onClick = {
                    showDialog.value = false
                }
            ) {
                Text(text = "NO")
            }
        }
    )
}