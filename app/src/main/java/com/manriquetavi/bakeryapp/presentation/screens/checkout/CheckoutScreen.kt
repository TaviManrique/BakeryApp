package com.manriquetavi.bakeryapp.presentation.screens.checkout

import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.util.ToastMessage
import kotlinx.coroutines.launch

@Composable
fun CheckoutScreen(
    screenNavController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ButtonAnimation()
        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
        ButtonAnimation2()
    }
}

@Composable
fun ButtonAnimation(
    animationDuration: Int = 100,
    scaleDown: Float = 0.9f
) {
    val context = LocalContext.current
    val interactionSource = MutableInteractionSource()

    val coroutineScope = rememberCoroutineScope()

    val scale = remember {
        Animatable(1f)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .scale(scale = scale.value)
            .background(
                color = Color(0xFF35898F),
                shape = RoundedCornerShape(size = 12f)
            )
            .clickable(interactionSource = interactionSource, indication = null) {
                coroutineScope.launch {
                    scale.animateTo(
                        scaleDown,
                        animationSpec = tween(animationDuration),
                    )
                    scale.animateTo(
                        1.1f,
                        animationSpec = tween(animationDuration),
                    )
                    scale.animateTo(
                        1.0f,
                        animationSpec = tween(animationDuration),
                    )
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Button Animation",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.button,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )
    }
}

@Composable
fun ButtonAnimation2() {
    val context = LocalContext.current
    val interactionSource = MutableInteractionSource()

    val coroutineScope = rememberCoroutineScope()

    val scale = remember {
        Animatable(1f)
    }
    Button(
        modifier = Modifier
            .scale(scale = scale.value),
        onClick = {
            coroutineScope.launch {
                scale.animateTo(
                    0.9f,
                    animationSpec = tween(1000),
                )
                scale.animateTo(
                    1.1f,
                    animationSpec = tween(1000),
                )
                scale.animateTo(
                    1.0f,
                    animationSpec = tween(1000),
                )
            }
            Toast.makeText(context, "Finish action", Toast.LENGTH_SHORT).show()
        },
        interactionSource = interactionSource
    ) {
        Text(
            text = "Button Animation2",
            style = MaterialTheme.typography.button,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun CheckoutScreenPreview() {
    CheckoutScreen(screenNavController = rememberNavController())
}