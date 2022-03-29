package com.manriquetavi.bakeryapp.presentation.screens.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manriquetavi.bakeryapp.R
import com.manriquetavi.bakeryapp.ui.theme.LightGray
import com.manriquetavi.bakeryapp.ui.theme.Shapes
import com.manriquetavi.bakeryapp.ui.theme.buttonBackgroundColor

@Composable
fun DetailsScreen() {
    Column {
        ParallaxToolbar()
        Content()
    }
}

@Composable
fun ParallaxToolbar() {
    TopAppBar(
        modifier = Modifier
            .height(400.dp),
        contentPadding = PaddingValues(),
        backgroundColor = Color.White
    ) {
        Column {
            Box(
                modifier = Modifier.height(344.dp)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.strawberry_pie_1),
                    contentDescription = "Image Food",
                    contentScale = ContentScale.Crop
                )
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.LightGray)
                            .padding(8.dp),
                        text = "Dessert",
                        style = MaterialTheme.typography.caption,
                        color = Color.Black
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = "Strawberry Pie",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun Content() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CounterFood()
        Price()
    }
}

@Composable
fun Price() {
    Text(
        text = "$ 0.99",
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun CounterFood() {
    Row(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, LightGray),
                shape = RoundedCornerShape(16.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { /*TODO add number of food*/ }
        ) {
            Icon(
                imageVector = Icons.Filled.Remove,
                tint = MaterialTheme.colors.buttonBackgroundColor,
                contentDescription = "Remove Icon",
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
                imageVector = Icons.Filled.Add,
                tint = MaterialTheme.colors.buttonBackgroundColor,
                contentDescription = "Add Remove",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen()
}
