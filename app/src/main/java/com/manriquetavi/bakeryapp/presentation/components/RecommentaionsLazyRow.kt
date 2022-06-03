package com.manriquetavi.bakeryapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import androidx.paging.LoadState
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.manriquetavi.bakeryapp.R
import com.manriquetavi.bakeryapp.domain.model.Food
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.presentation.screens.home.HomeViewModel
import com.manriquetavi.bakeryapp.ui.theme.Purple200
import com.manriquetavi.bakeryapp.ui.theme.Purple500

@Composable
fun RecommendationsLazyRow(
    homeViewModel: HomeViewModel,
    screenNavController: NavHostController
) {
    when(val allRecommendations = homeViewModel.allRecommendations.value) {
        is Response.Loading -> RecommendationsProgressBar()
        is Response.Success -> RecommendationsLazyRowContent(allRecommendations.data, screenNavController)
        is Response.Error -> {}
    }
}

@Composable
fun RecommendationsLazyRowContent(
    recommendations: List<Food>?,
    screenNavController: NavHostController
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        recommendations?.let {
            items(
                items = recommendations,
                key = {
                    it.id!!
                }
            ) { food ->
                RecommendationItem(food, screenNavController)
            }
        }
    }
}

@Composable
fun RecommendationItem(
    food: Food,
    screenNavController: NavHostController
) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .clickable { screenNavController.navigate(Screen.Details.passFoodId(food.id!!)) },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Purple200,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
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
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = food.name.toString(),
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = food.price.toString(),
                fontSize = MaterialTheme.typography.overline.fontSize
            )
        }
    }
}

@Composable
fun RecommendationsProgressBar() {
    Row(
        modifier = Modifier
            .height(160.dp)
            .fillMaxWidth()
    ) {
        ProgressBar()
    }
}

@Preview
@Composable
fun RecommendationsLazyRowContentPreview() {
    RecommendationItem(
        food = Food(),
        rememberNavController()
    )
}