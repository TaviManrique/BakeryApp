package com.manriquetavi.bakeryapp.presentation.screens.details

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.manriquetavi.bakeryapp.R
import com.manriquetavi.bakeryapp.domain.model.Food
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.presentation.components.ProgressBar
import com.manriquetavi.bakeryapp.ui.theme.LightGray
import com.manriquetavi.bakeryapp.ui.theme.buttonBackgroundColor
import com.manriquetavi.bakeryapp.util.Util

@Composable
fun DetailsScreen(
    screenNavController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {

    when(val selectedFood = detailsViewModel.selectedFood.value) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> DetailsScreenContent(selectedFood.data!!, screenNavController)
        is Response.Error -> Util.printError(selectedFood.message)
    }
}

@Composable
fun DetailsScreenContent(
    food: Food,
    screenNavController: NavHostController
) {
    Column {
        ParallaxToolbar(food)
        DetailsContent(food)
    }
    IconsToolbar(screenNavController)
}

@Composable
fun IconsToolbar(
    screenNavController: NavHostController
) {
    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier
                    .clickable { screenNavController.popBackStack() },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Icon",
                tint = MaterialTheme.colors.buttonBackgroundColor
            )

            Icon(
                modifier = Modifier
                    .clickable { },
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favorite Icon",
                tint = MaterialTheme.colors.buttonBackgroundColor
            )
        }
    }
}

@Composable
fun ParallaxToolbar(
    food: Food
) {
    TopAppBar(
        modifier = Modifier
            .height(400.dp),
        contentPadding = PaddingValues(),
        backgroundColor = Color.White
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(344.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
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
                        text = food.category.toString(),
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
                    text = food.name.toString(),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun DetailsContent(
    food: Food
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        //Price and Counter Food
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CounterFood()
            Price(food.price.toString())
        }

        //Add to cart button
        ButtonAddToCart()

        //Description food
        Description(food.description.toString())
    }

}

@Composable
fun Price(
    price: String
) {
    Text(
        text = price,
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
            onClick = {  }
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
            onClick = {  }
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                tint = MaterialTheme.colors.buttonBackgroundColor,
                contentDescription = "Add Remove",
            )
        }
    }
}

@Composable
fun ButtonAddToCart() {
    Button(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        onClick = { /*TODO button add to cart*/ },
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Add to cart"
        )
    }
}

@Composable
fun Description(
    description: String
) {
    LazyColumn {
        item {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = description,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(screenNavController = rememberNavController())
}
