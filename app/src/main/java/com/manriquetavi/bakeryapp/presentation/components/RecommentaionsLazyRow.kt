package com.manriquetavi.bakeryapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manriquetavi.bakeryapp.R

@Composable
fun RecommendationsLazyRow() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            RecommendationItem(
                title = "Apple Pie",
                price = "$ 10.0",
                imagePainter = painterResource(id = R.drawable.apple_pie)
            )
        }
        item {
            RecommendationItem(
                title = "Coffee Cappuccino",
                price = "$ 5.0",
                imagePainter = painterResource(id = R.drawable.coffee)
            )
        }
        item {
            RecommendationItem(
                title = "Cookie Chocolate",
                price = "$ 3.0",
                imagePainter = painterResource(id = R.drawable.cookie)
            )
        }
        item {
            RecommendationItem(
                title = "Cupcake Red Velvet",
                price = "$ 8.0",
                imagePainter = painterResource(id = R.drawable.cupcake)
            )
        }
        item {
            RecommendationItem(
                title = "Donut Classic",
                price = "$ 3.0",
                imagePainter = painterResource(id = R.drawable.donut)
            )
        }
    }
}

@Composable
fun RecommendationItem(
    title: String = "",
    price: String = "",
    imagePainter: Painter
) {
    Card(
        modifier = Modifier.width(160.dp)
    ) {
        Column(Modifier.padding(8.dp)) {
            Image(
                painter = imagePainter,
                contentDescription = "Image about Food",
                contentScale = ContentScale.Fit
            )
            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )
            Text(text = "$${price}",)
        }
    }
}

@Preview
@Composable
fun RecommendationsLazyRowPreview() {
    RecommendationsLazyRow()
}