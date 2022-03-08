package com.manriquetavi.bakeryapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manriquetavi.bakeryapp.R

@Composable
fun CategoriesLazyRow() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            CategoryItem(
                title = "Bread",
                icon = painterResource(id = R.drawable.item_bread),
                backgroundColor = Color(0xffFEF4E7)
            )
        }
        item {
            CategoryItem(
                title = "Cake",
                icon = painterResource(id = R.drawable.item_cake),
                backgroundColor = Color(0xffF6FBF3)
            )
        }
        item {
            CategoryItem(
                title = "Drink",
                icon = painterResource(id = R.drawable.item_drink),
                backgroundColor = Color(0xffFFFBF3)
            )
        }
        item {
            CategoryItem(
                title = "Wholewheat",
                icon = painterResource(id = R.drawable.item_wholewheat),
                backgroundColor = Color(0xffF6E6E9)
            )
        }
        item {
            CategoryItem(
                title = "Bread",
                icon = painterResource(id = R.drawable.item_bread)
            )
        }
        item {
            CategoryItem(
                title = "Cake",
                icon = painterResource(id = R.drawable.item_cake)
            )
        }
        item {
            CategoryItem(
                title = "Drink",
                icon = painterResource(id = R.drawable.item_drink)
            )
        }
        item {
            CategoryItem(
                title = "Wholewheat",
                icon = painterResource(id = R.drawable.item_wholewheat)
            )
        }
    }
}

@Composable
fun CategoryItem(
    title: String,
    backgroundColor: Color = Color.Transparent,
    icon: Painter
) {
    Card(
        modifier = Modifier.width(72.dp).clickable {  },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = backgroundColor,
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier.padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(48.dp),
                painter = icon,
                contentDescription = "Icon Category",
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                textAlign = TextAlign.Center,
                fontSize = 8.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesLazyRowPreview() {
    CategoriesLazyRow()
}