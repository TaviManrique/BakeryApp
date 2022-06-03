package com.manriquetavi.bakeryapp.presentation.components

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
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.manriquetavi.bakeryapp.domain.model.Promotion
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.presentation.screens.home.HomeViewModel
import com.manriquetavi.bakeryapp.util.Util


@ExperimentalCoilApi
@Composable
fun PromotionsLazyRow(
    homeViewModel: HomeViewModel
) {
    when(val allPromotions = homeViewModel.allPromotions.value) {
        is Response.Loading -> PromotionProgressBar()
        is Response.Success -> PromotionsLazyRowContent(allPromotions.data)
        is Response.Error -> Util.printError(allPromotions.message)
    }
}

@ExperimentalCoilApi
@Composable
fun PromotionsLazyRowContent(
    promotions: List<Promotion>?
) {
    LazyRow(
        modifier = Modifier.height(120.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        promotions?.let {
            items(
                items = promotions,
                key = { promotion ->
                    promotion.id!!
                }
            ) { promotion ->
                PromotionItem(promotion)
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun PromotionItem(
    promotion: Promotion
) {
    Card(
        modifier = Modifier
            .width(240.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = promotion.backgroundColor?.let { Color(it) }!!,
        elevation = 0.dp
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = promotion.title.toString(),
                    fontSize = 14.sp,
                    color = Color.White
                )
                Text(
                    text = promotion.subtitle.toString(),
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = promotion.header.toString(),
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight(),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(promotion.image)
                    .crossfade(2000)
                    .build(),
                alignment = Alignment.CenterEnd,
                contentScale = ContentScale.Crop,
                contentDescription = "Image Promotions"
            )
            /*
            Image(
                modifier = Modifier
                    .fillMaxHeight(),
                painter = painter,
                contentDescription = "",
                alignment = Alignment.CenterEnd,
                contentScale = ContentScale.Crop
            )*/
        }
    }
}

@Composable
fun PromotionProgressBar() {
    Row(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
    ) {
        ProgressBar()
    }
}

@ExperimentalCoilApi
@Preview
@Composable
fun PromotionsLazyRowPreview() {
    PromotionItem(
        Promotion()
    )
}