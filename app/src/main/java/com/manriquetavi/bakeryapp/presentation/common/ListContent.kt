package com.manriquetavi.bakeryapp.presentation.common

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.manriquetavi.bakeryapp.domain.model.Food
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.ui.theme.*

@Composable
fun FoodItem(
    food: Food,
    screenNavController: NavHostController,
    isFoodCartItem: Boolean = false
) {
    val painter = rememberImagePainter(food.image)

    Card(
        modifier = Modifier
            .padding(EXTRA_SMALL_PADDING)
            .fillMaxWidth()
            .height(FOOD_ITEM_HEIGHT)
            .clickable { if(!isFoodCartItem) screenNavController.navigate(Screen.Details.passFoodId(food.id!!)) },
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
                Image(
                    painter = painter,
                    contentDescription = "Food Image"
                )
            }
            Column(
                modifier = Modifier.weight(0.4f)
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
            Column(
                modifier = Modifier
                    .alpha(if (!isFoodCartItem) 0f else 1f)
                    .border(
                        BorderStroke(1.dp, LightGray),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .weight(0.1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = { Log.d("FoodItem", "add item") },
                    enabled = isFoodCartItem
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        tint = MaterialTheme.colors.buttonBackgroundColor,
                        contentDescription = "Add Icon",
                    )
                }
                Text(
                    text = "1",
                    style = MaterialTheme.typography.h6
                )
                IconButton(
                    onClick = { Log.d("FoodItem", "minues item") },
                    enabled = isFoodCartItem
                ) {
                    Icon(
                        imageVector = Icons.Filled.Remove,
                        tint = MaterialTheme.colors.buttonBackgroundColor,
                        contentDescription = "Remove Icon",
                    )
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
        screenNavController = rememberNavController(),
        isFoodCartItem = false
    )
}
