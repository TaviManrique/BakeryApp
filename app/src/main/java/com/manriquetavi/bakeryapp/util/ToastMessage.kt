package com.manriquetavi.bakeryapp.util

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ToastMessage(
    duration: Int,
    message: String
) {
    val context = LocalContext.current
    Toast.makeText(context, message, duration).show()
}