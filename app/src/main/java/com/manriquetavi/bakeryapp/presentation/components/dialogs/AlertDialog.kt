package com.manriquetavi.bakeryapp.presentation.components.dialogs

import android.widget.Toast
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
    foodCart: FoodCart,
    cartViewModel: CartViewModel
)
{
    val context = LocalContext.current
    AlertDialog(
        onDismissRequest = {
            showDialog.value = false
                           },
        title = {
            Text(
                text = "Title"
            ) },
        text = {
            Text(
                text = "Text"
            ) },
        confirmButton = {
            TextButton(onClick = {
                foodCart.id?.let { cartViewModel.deleteFoodCart(it) }
                showDialog.value = false
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