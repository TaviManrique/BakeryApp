package com.manriquetavi.bakeryapp.presentation.components

import com.manriquetavi.bakeryapp.R
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.manriquetavi.bakeryapp.domain.model.Category
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.presentation.screens.home.HomeViewModel
import com.manriquetavi.bakeryapp.util.Util

@ExperimentalCoilApi
@Composable
fun CategoriesLazyRow(
    homeViewModel: HomeViewModel,
    screenNavController: NavHostController
) {
    when(val allCategories = homeViewModel.allCategories.value) {
        is Response.Loading -> CategoryProgressBar()
        is Response.Success -> CategoriesLazyRowContent(categories = allCategories.data, screenNavController = screenNavController)
        is Response.Error -> Util.printError(allCategories.message)
    }
}

@ExperimentalCoilApi
@Composable
fun CategoriesLazyRowContent(
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

@ExperimentalCoilApi
@Composable
fun CategoryItem(
    category: Category,
    screenNavController: NavHostController
) {
    Card(
        modifier = Modifier
            .width(72.dp)
            .clickable { screenNavController.navigate(Screen.Search.passCategory(category = category.name))},
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                modifier = Modifier.size(48.dp),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(category.image)
                    .crossfade(2000)
                    .build(),
                placeholder = painterResource(R.drawable.ic_placeholder),
                error = painterResource(R.drawable.ic_placeholder),
                contentScale = ContentScale.Crop,
                contentDescription = "Icon Category"
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

@ExperimentalCoilApi
@Composable
fun CategoryProgressBar() {
    Row(
        modifier = Modifier
            .height(72.dp)
            .fillMaxWidth()
    ) {
        ProgressBarCircular()
    }
}

@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun CategoriesLazyRowPreview() {
    CategoryItem(
        category = Category(),
        rememberNavController()
    )
}