package com.manriquetavi.bakeryapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.manriquetavi.bakeryapp.R
import com.manriquetavi.bakeryapp.domain.model.Category

@Composable
fun CategoriesLazyRow(
    categories: List<Category>?,
    screenNavController: NavHostController
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        categories?.let {
            items(
                items = categories,
                key = { category ->
                    category.id!!
                }
            ) { category ->
                CategoryItem(category, screenNavController)
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    screenNavController: NavHostController
) {
    Card(
        modifier = Modifier
            .width(72.dp)
            .clickable { },
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(48.dp),
                painter = rememberImagePainter(category.image),
                contentDescription = "Icon Category",
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = category.name.toString(),
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
    CategoriesLazyRow(
        listOf<Category>(),
        rememberNavController()
    )
}