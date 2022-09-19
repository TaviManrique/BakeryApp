package com.manriquetavi.bakeryapp.presentation.screens.details

import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
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
import com.manriquetavi.bakeryapp.domain.model.FoodCart
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.presentation.components.ProgressBarCircular
import com.manriquetavi.bakeryapp.presentation.components.dialogs.DialogBoxProgressAnimation
import com.manriquetavi.bakeryapp.ui.theme.LightGray
import com.manriquetavi.bakeryapp.ui.theme.buttonBackgroundColor
import com.manriquetavi.bakeryapp.util.ToastMessage
import com.manriquetavi.bakeryapp.util.Util
import kotlinx.coroutines.launch

@Composable
fun DetailsScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {

    when(val selectedFood = detailsViewModel.selectedFood.value) {
        is Response.Loading -> ProgressBarCircular()
        is Response.Success -> selectedFood.data?.let { DetailsScreenContent(it, navController, detailsViewModel) }
        is Response.Error -> Util.printError(selectedFood.message)
    }
}

@Composable
fun DetailsScreenContent(
    food: Food,
    navController: NavHostController,
    detailsViewModel: DetailsViewModel
) {
    Column {
        ParallaxToolbar(food)
        DetailsContent(food, detailsViewModel)
    }
    IconsToolbar(navController)
}

@Composable
fun IconsToolbar(
    screenNavController: NavHostController
) {
    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { screenNavController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = MaterialTheme.colors.buttonBackgroundColor
                )
            }
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
    food: Food,
    detailsViewModel: DetailsViewModel
) {
    val countFood = remember { mutableStateOf(1) }

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
            CounterFood(countFood)
            food.price?.let { price ->
                Price(price = price.toDouble(), countFood = countFood.value)
            }
        }

        //Add to cart button
        ButtonAddToCart(food,detailsViewModel, countFood)

        //Description food
        Description(food.description.toString())
    }
}

@Composable
fun Price(
    price: Double,
    countFood: Int
) {
    Text(
        text = String.format("%.2f", price*countFood),
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun CounterFood(
    countFood: MutableState<Int>
) {
    Row(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, LightGray),
                shape = RoundedCornerShape(16.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { if(countFood.value != 20) countFood.value++ }
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                tint = MaterialTheme.colors.buttonBackgroundColor,
                contentDescription = "Add Icon",
            )
        }
        Text(
            text = countFood.value.toString(),
            style = MaterialTheme.typography.h6
        )
        IconButton(
            onClick = { if(countFood.value != 1) countFood.value-- }
        ) {
            Icon(
                imageVector = Icons.Filled.Remove,
                tint = MaterialTheme.colors.buttonBackgroundColor,
                contentDescription = "Remove Icon",
            )
        }
    }
}

@Composable
fun ButtonAddToCart(
    food: Food,
    detailsViewModel: DetailsViewModel,
    countFood: MutableState<Int>
) {
    val openDialog by detailsViewModel.openDialog.observeAsState(false)
    val showToast by detailsViewModel.showToast.observeAsState(false)
    val coroutineScope = rememberCoroutineScope()
    val scale = remember {
        Animatable(1f)
    }
    if (openDialog) {
        //detailsViewModel.startThread()
        DialogBoxProgressAnimation()
    }

    if (showToast) {
        ToastMessage(duration = Toast.LENGTH_LONG, message = "Food item added")
        detailsViewModel.showToast.value = false
    }
    Button(
        modifier = Modifier
            .scale(scale = scale.value)
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        onClick = {
            coroutineScope.launch {
                //Animation button
                scale.animateTo(
                    0.95f,
                    animationSpec = tween(100),
                )
                scale.animateTo(
                    1.05f,
                    animationSpec = tween(100),
                )
                scale.animateTo(
                    1.0f,
                    animationSpec = tween(100),
                )
                detailsViewModel.insertFoodCart(
                    FoodCart(
                        id = food.id.toString(),
                        name = food.name,
                        category = food.category,
                        image = food.image,
                        description = food.description,
                        quantity = countFood.value,
                        price = food.price?.toDouble()
                    )
                )
            }
                  },
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
    DetailsScreen(navController = rememberNavController())
}
