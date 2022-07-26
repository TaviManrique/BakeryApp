package com.manriquetavi.bakeryapp.presentation.screens.cart

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.domain.model.FoodCart
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.presentation.common.FoodCartItem
import com.manriquetavi.bakeryapp.ui.theme.Purple500
import com.manriquetavi.bakeryapp.ui.theme.SMALL_PADDING
import kotlinx.coroutines.launch

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
        CartScreenContent(foodsCart, cartViewModel, screenNavController)
    }
}

@Composable
fun CartScreenContent(
    foodsCart: List<FoodCart>,
    cartViewModel: CartViewModel,
    screenNavController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 16.dp, end = 16.dp, bottom = 70.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ListFoodCart(foodsCart,cartViewModel)
        TotalPrice(foodsCart)
        BottomCheckout(screenNavController)
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
            .height(460.dp),
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


    Text(
        text = "Total: $${String.format("%.2f",totalPrice.value)}",
        style = MaterialTheme.typography.h5
    )

}

@Composable
fun BottomCheckout(screenNavController: NavHostController) {

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
                screenNavController.navigate(Screen.Checkout.route)
            }
                  },
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
        cartViewModel = hiltViewModel(),
        screenNavController = rememberNavController()
    )
}

