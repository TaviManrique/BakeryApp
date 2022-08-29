package com.manriquetavi.bakeryapp.presentation.screens.order

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.domain.model.Order
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.presentation.common.OrderItem
import com.manriquetavi.bakeryapp.presentation.components.ProgressBarCircular
import com.manriquetavi.bakeryapp.util.Util


@Composable
fun OrderScreen(
    navController: NavHostController,
    paddingValues: PaddingValues,
    orderViewModel: OrderViewModel = hiltViewModel()
) {

    when(val allOrders = orderViewModel.allOrders.value) {
        is Response.Loading -> ProgressBarCircular()
        is Response.Success -> OrderScreenContent(allOrders.data, navController, paddingValues)
        is Response.Error -> Util.printError(allOrders.message)
    }
}

@Composable
fun OrderScreenContent(
    orders: List<Order>?,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        orders?.let {
            items(
                items = orders,
                key = { order ->
                    order.id!!
                }
            ) { order ->
                OrderItem(order = order, navController = navController)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun OrderScreenPreview() {
    OrderScreen(
        navController = rememberNavController(),
        paddingValues = PaddingValues()
    )
}