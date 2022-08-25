package com.manriquetavi.bakeryapp.presentation.screens.checkout

import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.domain.model.FoodCart
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.navigation.BottomBarScreen
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.presentation.components.CashInputField
import com.manriquetavi.bakeryapp.presentation.components.DropDownAddress
import com.manriquetavi.bakeryapp.presentation.components.ProgressBarCircular
import com.manriquetavi.bakeryapp.presentation.components.RadioButtonPayments
import com.manriquetavi.bakeryapp.presentation.screens.cart.TotalPrice
import com.manriquetavi.bakeryapp.util.ToastMessage
import com.manriquetavi.bakeryapp.util.Util
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CheckoutScreen(
    navController: NavHostController,
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val wasButtonDonePressed by checkoutViewModel.wasButtonDonePressed.observeAsState(false)
    val response = checkoutViewModel.addOrderState.value
    Scaffold(
        topBar = { CheckoutTopBar(screenNavController = navController) },
        backgroundColor = Color.Transparent
    ) { paddingValues ->
        CheckoutContent(navController, paddingValues, checkoutViewModel)
    }

    when (response) {
        is Response.Loading -> ProgressBarCircular()
        is Response.Success ->
            if (wasButtonDonePressed) {
                LaunchedEffect(true) {
                    Toast.makeText(context, "Success send order", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                    navController.navigate(BottomBarScreen.Order.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                    navController.navigate(Screen.Track.route)
                    checkoutViewModel.deleteAllFoodCart()
                }
            }
        is Response.Error -> {
            Util.printError(response.message)
            ToastMessage(duration = Toast.LENGTH_SHORT, message = "Error send order")
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CheckoutContent(
    navController: NavHostController,
    paddingValues: PaddingValues,
    checkoutViewModel: CheckoutViewModel
) {
    val foodsCart = checkoutViewModel.foodCardList.collectAsState().value

    val radioOptions = listOf("Cash", "Plin or Yape", "Debit or Credit Card (not available)")
    val selectedItem = rememberSaveable { mutableStateOf("") }
    val cash = rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val validateCash = rememberSaveable { mutableStateOf(true) }
    val selectedAddress = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = "Delivery",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DropDownAddress(
                modifier = Modifier.weight(0.9f),
                selectedAddress = selectedAddress
            )
            IconButton(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.1f),
                onClick = { navController.navigate(Screen.Location.route) }
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize(),
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
        TotalPrice(foodsCart = foodsCart)
        BottomDone(
            foodCarts = foodsCart,
            cash = cash,
            selectedItem = selectedItem,
            validateCash = validateCash,
            selectedAddress = selectedAddress,
            checkoutViewModel = checkoutViewModel
        )
    }
}


@Composable
fun BottomDone(
    foodCarts: List<FoodCart>,
    cash: MutableState<String>,
    selectedItem: MutableState<String>,
    validateCash: MutableState<Boolean>,
    selectedAddress: MutableState<String>,
    checkoutViewModel: CheckoutViewModel
) {

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
                    checkoutViewModel.addOrder(foodCarts, selectedAddress.value)
                } else {
                    if (
                        validateDataCheckout(
                            cash = cash.value,
                            validateCash = validateCash
                        )
                    ) {
                        //SEND ORDER AND WAIT ORDER RESPONSE
                        checkoutViewModel.addOrder(foodCarts, selectedAddress.value)
                    }
                }
            }
        },
        shape = RoundedCornerShape(16.dp),
        enabled = selectedItem.value.isNotEmpty() && selectedAddress.value.isNotEmpty()
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
    CheckoutScreen(navController = rememberNavController())
}