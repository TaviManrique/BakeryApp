package com.manriquetavi.bakeryapp.presentation.common

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.manriquetavi.bakeryapp.R
import com.manriquetavi.bakeryapp.domain.model.Food
import com.manriquetavi.bakeryapp.domain.model.FoodCart
import com.manriquetavi.bakeryapp.domain.model.FoodOrder
import com.manriquetavi.bakeryapp.domain.model.Order
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.presentation.components.dialogs.AlertDialogCommon
import com.manriquetavi.bakeryapp.presentation.screens.cart.CartViewModel
import com.manriquetavi.bakeryapp.ui.theme.*
import com.manriquetavi.bakeryapp.util.removeLastNchars
import java.util.*

@Composable
fun FoodItem(
    food: Food,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .padding(EXTRA_SMALL_PADDING)
            .fillMaxWidth()
            .height(FOOD_ITEM_HEIGHT)
            .clickable { navController.navigate(Screen.Details.passFoodId(food.id!!)) },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .padding(SMALL_PADDING)
                    .weight(0.5f)
                    .size(150.dp),
                elevation = 4.dp,
                color = Color.Transparent
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White),
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(food.image)
                        .crossfade(500)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_placeholder),
                    error = painterResource(R.drawable.ic_placeholder),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Image Food"
                )
            }
            Column(
                modifier = Modifier.weight(0.5f)
            ) {
                Text(
                    text = food.name.toString(),
                    style = MaterialTheme.typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = food.category.toString(),
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = food.price.toString(),
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

@Composable
fun FoodCartItem(
    foodCart: FoodCart,
    cartViewModel: CartViewModel,
) {
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) AlertDialogCommon(
        title = "Delete",
        text = "Are you sure to remove this item?",
        showDialog = showDialog
    ) {
        foodCart.id?.let { cartViewModel.deleteFoodCart(it) }
    }
    Card(
        modifier = Modifier
            .padding(EXTRA_SMALL_PADDING)
            .fillMaxWidth()
            .height(FOOD_ITEM_HEIGHT),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Purple200
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(SMALL_PADDING)
                    .weight(0.5f)
                    .size(150.dp),
                elevation = 4.dp,
                color = Color.Transparent
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White),
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(foodCart.image)
                        .crossfade(500)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_placeholder),
                    error = painterResource(R.drawable.ic_placeholder),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Image Food",
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(SMALL_PADDING)
                    .height(150.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = foodCart.name.toString(),
                        style = MaterialTheme.typography.h6,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = foodCart.category.toString(),
                        style = MaterialTheme.typography.caption,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = String.format("%.2f",foodCart.quantity?.let { foodCart.price?.times(it) }),
                        style = MaterialTheme.typography.h6,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                            .border(
                                BorderStroke(1.dp, LightGray),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                foodCart.id?.let { cartViewModel.increaseQuantityFoodCart(it) }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                tint = MaterialTheme.colors.buttonBackgroundColor,
                                contentDescription = "Add Icon",
                            )
                        }
                        Text(
                            text = foodCart.quantity.toString(),
                            style = MaterialTheme.typography.h6
                        )
                        IconButton(
                            onClick = {
                                if (foodCart.quantity != 1) {
                                    foodCart.id?.let { cartViewModel.minusQuantityFoodCart(it) }
                                } else {
                                    showDialog.value = true
                                    //foodCart.id?.let { cartViewModel.deleteFoodCart(it) }
                                }
                            }
                        ) {
                            if (foodCart.quantity != 1) {
                                Icon(
                                    imageVector = Icons.Filled.Remove,
                                    tint = MaterialTheme.colors.buttonBackgroundColor,
                                    contentDescription = "Remove Icon",
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    tint = MaterialTheme.colors.buttonBackgroundColor,
                                    contentDescription = "Remove Icon",
                                )
                            }
                        }
                    }
                    if (foodCart.quantity != 1) {
                        IconButton(
                            onClick = {
                                showDialog.value = true
                                //foodCart.id?.let { cartViewModel.deleteFoodCart(it) }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                tint = MaterialTheme.colors.buttonBackgroundColor,
                                contentDescription = "Remove Icon"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OrderItem(
    order: Order,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .padding(EXTRA_SMALL_PADDING)
            .fillMaxWidth()
            .height(90.dp)
            .clickable { navController.navigate(Screen.Track.passOrderId(orderId = order.id!!)) },
        elevation = 6.dp,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            1.dp,
            when (order.status) {
                0 -> Color.Red.copy(0.5f)
                1,2,3,4 -> Color.Green.copy(0.5f)
                else -> Color.DarkGray
            }
        )
    ) {
        Row(
            modifier = Modifier
                .padding(EXTRA_SMALL_PADDING)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(0.9f)
            ) {
                Text(
                    text = "Order: ${formatFoodOrdersToString(order.foodOrders)}",
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Address: ${order.address}",
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Date: ${dateToStringFormat(order.date!!.toDate())}",
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text =
                    when(order.status) {
                        0 -> "Canceled"
                        1,2,3,4 -> "In Process..."
                        5 -> "Completed"
                        else -> { "Unknown" }
                    },
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Light,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Icon(
                modifier = Modifier
                    .weight(0.1f)
                    .size(25.dp),
                imageVector = Icons.Filled.NavigateNext,
                contentDescription = "More Details Icon",
                tint = Color.DarkGray
            )
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FoodItemPreview() {
    FoodCartItem(
        foodCart = FoodCart(
            id = "1",
            name = "Name",
            category = "category",
            image = "https://firebasestorage.googleapis.com/v0/b/bakeryapp-d3dfa.appspot.com/o/categories_images%2Fcategory_cracker.png?alt=media&token=a6855c95-4b1d-4f29-b1be-b79200b87d90",
            description = "description",
            quantity = 1,
            price = 10.0
        ),
        cartViewModel = hiltViewModel()
    )
}

fun dateToStringFormat(date: Date): String {
    val dateSplit = date.toString().split(" ")
    val month = when (dateSplit[1]) {
        "Jan" -> 1
        "Feb" -> 2
        "Mar" -> 3
        "Apr" -> 4
        "May" -> 5
        "Jun" -> 6
        "Jul" -> 7
        "Aug" -> 8
        "Sep" -> 9
        "Oct" -> 10
        "Nov" -> 11
        "Dec" -> 12
        else -> {
            0
        }
    }
    val timeSplit = dateSplit[3].split(":")
    return timeSplit[0] + ":" + timeSplit[1] + "  " + dateSplit[2] + "/" + month + "/" + dateSplit[5]
}

fun formatFoodOrdersToString(foodOrders: Map<String, FoodOrder>): String {
    val response = StringBuilder()
    foodOrders.keys.forEach {
        response.append(foodOrders[it]?.name).append(", ")
    }
    return removeLastNchars(response.toString(), 2)
}

@Preview
@Composable
fun OrderItem1Preview() {
    OrderItem(
        Order(
            id = "ASD123ASD123",
            address = "Av. Javier Prado 1234",
            status = 2
        ),
        rememberNavController()
    )
}
@Preview
@Composable
fun OrderItem2Preview() {
    OrderItem(
        Order(
            id = "ASD123ASD123",
            address = "Av. Javier Prado 1234",
            status = 1
        ),
        rememberNavController()
    )
}
