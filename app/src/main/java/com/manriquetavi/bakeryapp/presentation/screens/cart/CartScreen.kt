package com.manriquetavi.bakeryapp.presentation.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.manriquetavi.bakeryapp.domain.model.FoodCart
import com.manriquetavi.bakeryapp.presentation.common.FoodCartItem
import com.manriquetavi.bakeryapp.ui.theme.Purple500
import com.manriquetavi.bakeryapp.ui.theme.SMALL_PADDING

@Composable
fun CartScreen(
    screenNavController: NavHostController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val foodsCart = cartViewModel.foodCardList.collectAsState().value
    Scaffold(
        topBar = {},
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 50.dp),
                onClick = { cartViewModel.deleteAllFoodCart() }
            ) {

            }
        }
    ) {
        CartScreenContent(foodsCart, cartViewModel)
    }
}

@Composable
fun CartScreenContent(
    foodsCart: List<FoodCart>,
    cartViewModel: CartViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 16.dp, end = 16.dp, bottom = 70.dp)
            .background(color = Color.Blue),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ListFoodCart(foodsCart,cartViewModel)
        TotalPrice(foodsCart)
        BottomCheckout()
    }
}

@Composable
fun ListFoodCart(
    foodsCart: List<FoodCart>,
    cartViewModel: CartViewModel
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = SMALL_PADDING)
            .fillMaxWidth()
            .height(480.dp)
            .background(color = Color.Red),
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),
    ) {
        foodsCart?.let {
            items(
                items = foodsCart,
                key = { foodCart ->
                    foodCart.id!!
                }
            ) { foodCart ->
                FoodCartItem(foodCart = foodCart, cartViewModel = cartViewModel)
            }
        }
    }
}

@Composable
fun TotalPrice(foodsCart: List<FoodCart>) {
    var aux = 0.00
    val totalPrice = remember {
        mutableStateOf(0.00)
    }
    foodsCart.forEach { foodCart ->
        foodCart.price?.let {
            aux += foodCart.quantity?.times(it)!!
        }
    }
    totalPrice.value = aux

    Box(
        modifier = Modifier
            .background(color = Purple500)
    ) {
        Text(
            text = "Total: $${String.format("%.2f",totalPrice.value)}",
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
fun BottomCheckout() {
    Button(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        onClick = {  },
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Checkout"
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun CartScreenPreview() {
    CartScreenContent(
        foodsCart = listOf(
            FoodCart(

            )
        ),
        cartViewModel = hiltViewModel()
    )
}

