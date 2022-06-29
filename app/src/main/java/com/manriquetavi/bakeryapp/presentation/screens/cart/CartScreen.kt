package com.manriquetavi.bakeryapp.presentation.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.domain.model.FoodCart
import com.manriquetavi.bakeryapp.presentation.common.FoodCartItem
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
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        CartScreenContent(foodsCart)
    }
}

@Composable
fun CartScreenContent(
    foodsCart: List<FoodCart>
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        ListFoodCart(foodsCart)
    }
}

@Composable
fun ListFoodCart(
    foodsCart: List<FoodCart>
) {
    LazyColumn(
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {
        foodsCart?.let {
            items(
                items = foodsCart,
                key = { foodCart ->
                    foodCart.id!!
                }
            ) { foodCart ->
                FoodCartItem(foodCart = foodCart)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CartScreenPreview() {
    CartScreen(screenNavController = rememberNavController())
}

