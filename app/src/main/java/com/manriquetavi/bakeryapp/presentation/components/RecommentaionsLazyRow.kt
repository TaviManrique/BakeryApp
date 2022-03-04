package com.manriquetavi.bakeryapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manriquetavi.bakeryapp.R
import com.manriquetavi.bakeryapp.ui.theme.Purple500

@Composable
fun RecommendationsLazyRow() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            RecommendationItem(
                title = "Apple Pie",
                price = "10.0",
                imagePainter = painterResource(id = R.drawable.apple_pie)
            )
        }
        item {
            RecommendationItem(
                title = "Coffee Cappuccino",
                price = "5.0",
                imagePainter = painterResource(id = R.drawable.coffee)
            )
        }
        item {
            RecommendationItem(
                title = "Cookie Chocolate",
                price = "3.0",
                imagePainter = painterResource(id = R.drawable.cookie)
            )
        }
        item {
            RecommendationItem(
                title = "Cupcake Red Velvet",
                price = "8.0",
                imagePainter = painterResource(id = R.drawable.cupcake)
            )
        }
        item {
            RecommendationItem(
                title = "Donut Classic",
                price = "3.0",
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
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Purple500),
                painter = imagePainter,
                contentDescription = "Image about Food"
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = title,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "$ $price",
                fontSize = MaterialTheme.typography.overline.fontSize
            )
        }
    }
}

@Preview
@Composable
fun RecommendationsLazyRowPreview() {
    RecommendationsLazyRow()
}