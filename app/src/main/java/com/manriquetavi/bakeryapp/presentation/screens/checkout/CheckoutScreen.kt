package com.manriquetavi.bakeryapp.presentation.screens.checkout

import android.util.Patterns
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.presentation.components.CashInputField
import com.manriquetavi.bakeryapp.presentation.components.DropDownAddress
import com.manriquetavi.bakeryapp.presentation.components.RadioButtonPayments
import com.manriquetavi.bakeryapp.ui.theme.SMALL_PADDING
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CheckoutScreen(
    screenNavController: NavHostController
) {

    Scaffold(
        topBar = { CheckoutTopBar(screenNavController = screenNavController) }
    ) {
        CheckoutContent(screenNavController)
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CheckoutContent(
    screenNavController: NavHostController
) {
    val radioOptions = listOf("Cash", "Plin or Yape", "Debit or Credit Card (not available)")
    val selectedItem = remember { mutableStateOf("") }
    val cash = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val state = rememberLazyListState()
    val validateCash = rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = "Delivery",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        Row() {
            DropDownAddress()
            IconButton(
                onClick = { screenNavController.navigate(Screen.Location.route) }
            ) {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = SMALL_PADDING)
                        .size(32.dp),
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Icon Profile Item",
                    tint = MaterialTheme.colors.primary
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = "Payment",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        RadioButtonPayments(
            radioOptions,
            selectedItem
        )
        if (selectedItem.value == "Cash") {
            CashInputField(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .bringIntoViewRequester(bringIntoViewRequester)
                    .onFocusEvent { focusState ->
                        if (focusState.isFocused) {
                            coroutineScope.launch {
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    }
                    .padding(top = 24.dp),
                text = cash,
                focusManager = focusManager,
                errorMessage = "Please, input a valid cash",
                isError = !validateCash.value,
            )
        }
        BottomDone(
            screenNavController = screenNavController,
            cash = cash,
            selectedItem = selectedItem,
            validateCash = validateCash
        )
    }
}


@Composable
fun BottomDone(
    screenNavController: NavHostController,
    cash: MutableState<String>,
    selectedItem: MutableState<String>,
    validateCash: MutableState<Boolean>
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val scale = remember {
        Animatable(1f)
    }
    Button(
        modifier = Modifier
            .scale(scale = scale.value)
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        onClick = {
            //Animation button
            coroutineScope.launch {
                scale.animateTo(
                    0.95f,
                    animationSpec = tween(100),
                )
                scale.animateTo(
                    1.05f,
                    animationSpec = tween(100),
                )
                scale.animateTo(
                    1.0f,
                    animationSpec = tween(100),
                )
                if (selectedItem.value != "Cash") {
                    //SEND ORDER AND WAIT ORDER RESPONSE
                    Toast.makeText(context, "SEND ORDER", Toast.LENGTH_SHORT).show()
                } else {
                    if (
                        validateDataCheckout(
                            cash = cash.value,
                            validateCash = validateCash
                        )
                    ) {
                        //SEND ORDER AND WAIT ORDER RESPONSE
                        Toast.makeText(context, "SEND ORDER", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        },
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Done"
        )
    }
}

fun validateDataCheckout(
    cash: String,
    validateCash: MutableState<Boolean>
): Boolean {
    val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
    validateCash.value = cash.matches(regex)
    return validateCash.value
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showSystemUi = true)
@Composable
fun CheckoutScreenPreview() {
    CheckoutScreen(screenNavController = rememberNavController())
}