package com.manriquetavi.bakeryapp.presentation.common

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.ui.theme.*

@Composable
fun FoodItem(
    screenNavController: NavHostController
) {
    val painter = rememberImagePainter(data = "")

    Card(
        modifier = Modifier
            .padding(EXTRA_SMALL_PADDING)
            .fillMaxWidth()
            .height(FOOD_ITEM_HEIGHT)
            .clickable { screenNavController.navigate(Screen.Details.passFoodId(1)) },
        shape = RoundedCornerShape(8.dp),
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
                    text = "Donut",
                    style = MaterialTheme.typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Dessert",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "$ 0.99",
                    style = MaterialTheme.typography.h6
                )
            }
            Column(
                modifier = Modifier
                    .alpha(1f)/*TODO to make visible or invisible*/
                    .border(
                        BorderStroke(1.dp, LightGray),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .weight(0.1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = { /*TODO add number of food*/ }
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
                    onClick = { /*TODO minus number of food*/ }
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

@Preview(showBackground = true)
@Composable
fun FoodItemPreview() {
    FoodItem( screenNavController = rememberNavController())
}
