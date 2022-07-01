package com.manriquetavi.bakeryapp.presentation.common

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.manriquetavi.bakeryapp.R
import com.manriquetavi.bakeryapp.domain.model.Food
import com.manriquetavi.bakeryapp.domain.model.FoodCart
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.presentation.screens.cart.CartViewModel
import com.manriquetavi.bakeryapp.ui.theme.*

@Composable
fun FoodItem(
    food: Food,
    screenNavController: NavHostController
) {
    Card(
        modifier = Modifier
            .padding(EXTRA_SMALL_PADDING)
            .fillMaxWidth()
            .height(FOOD_ITEM_HEIGHT)
            .clickable { screenNavController.navigate(Screen.Details.passFoodId(food.id!!)) },
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
                elevation = 4.dp
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White),
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(food.image)
                        .crossfade(2000)
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
    Card(
        modifier = Modifier
            .padding(EXTRA_SMALL_PADDING)
            .fillMaxWidth()
            .height(FOOD_ITEM_HEIGHT),
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
                elevation = 4.dp
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White),
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(foodCart.image)
                        .crossfade(2000)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_placeholder),
                    error = painterResource(R.drawable.ic_placeholder),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Image Food"
                )
            }
            Column(
                modifier = Modifier.weight(0.3f)
            ) {
                Text(
                    text = foodCart.name.toString(),
                    style = MaterialTheme.typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = foodCart.category.toString(),
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = String.format("%.2f",foodCart.quantity?.let { foodCart.price?.times(it) }),
                    style = MaterialTheme.typography.h6
                )
            }
            Column(
                modifier = Modifier
                    .padding(SMALL_PADDING)
                    .border(
                        BorderStroke(1.dp, LightGray),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .weight(0.2f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = { foodCart.id?.let { cartViewModel.increaseQuantityFoodCart(it) } }
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
                        if (foodCart.quantity != 0) {
                            foodCart.id?.let { cartViewModel.minusQuantityFoodCart(it) }
                        } else {
                            foodCart.id?.let { cartViewModel.deleteFoodCart(it) }
                        }
                    }
                ) {
                    if (foodCart.quantity != 0) {
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
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FoodItemPreview() {
    FoodItem(
        food = Food(),
        screenNavController = rememberNavController()
    )
}
