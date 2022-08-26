package com.manriquetavi.bakeryapp.presentation.screens.order

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.domain.model.Order
import com.manriquetavi.bakeryapp.presentation.common.OrderItem


@Composable
fun OrderScreen(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    val orders = listOf(
        Order(
            id = "ASD123ASD123",
            address = "Av. Javier Prado 1234",
            status = 1
        ),
        Order(
            id = "DFGH1234SDFG",
            address = "Av. Javier Prado 1234",
            status = 2
        ),
        Order(
            id = "KFJFHS9756SA",
            address = "Av. Javier Prado 1234",
            status = 5
        ),
        Order(
            id = "ASD123ASD124",
            address = "Av. Javier Prado 1234",
            status = 5
        ),
        Order(
            id = "DFGH1234SDFH",
            address = "Av. Javier Prado 1234",
            status = 5
        ),
        Order(
            id = "KFJFHS9756SB",
            address = "Av. Javier Prado 1234",
            status = 5
        ),
        Order(
            id = "ASD123ASD122",
            address = "Av. Javier Prado 1234",
            status = 5
        ),
        Order(
            id = "DFGH1234SDFJ",
            address = "Av. Javier Prado 1234",
            status = 5
        ),
        Order(
            id = "KFJFHS9756SQ",
            address = "Av. Javier Prado 1234",
            status = 5
        )
    )
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(
            items = orders,
            key = { order ->
                order.id
            }
        ) { order ->
            OrderItem(order = order, navController = navController)
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