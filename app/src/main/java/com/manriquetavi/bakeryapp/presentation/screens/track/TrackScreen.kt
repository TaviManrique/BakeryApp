package com.manriquetavi.bakeryapp.presentation.screens.track

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.R
import com.manriquetavi.bakeryapp.ui.theme.*

@Composable
fun TrackScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = { TrackTopBar(navController) },
        backgroundColor = Color.Transparent
    ) { paddingValues ->
        TrackScreenContent(paddingValues)
    }
}

@Composable
fun TrackScreenContent(
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Estimated time of delivery",
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.descriptionColor,
            fontWeight = FontWeight.Normal,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "18:00 - 18:15",
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.titleColor,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Image(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .width(240.dp)
                .height(150.dp),
            painter = painterResource(id = R.drawable.order_state),
            contentDescription = "Image Order State",
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            LinearProgressIndicator(modifier = Modifier.weight(1f), progress = 1f)
            Spacer(modifier = Modifier.weight(0.2f))
            LinearProgressIndicator(modifier = Modifier.weight(2f))
            Spacer(modifier = Modifier.weight(0.2f))
            LinearProgressIndicator(modifier = Modifier.weight(2f), progress = 0f)
            Spacer(modifier = Modifier.weight(0.2f))
            LinearProgressIndicator(modifier = Modifier.weight(1f), progress = 0f)
        }
        Text(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            text = "We've got you a rider! They're heading to the restaurant.",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.titleColor,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        DetailOrderCard()
    }
}

@Composable
fun DetailOrderCard() {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = SMALL_PADDING)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.MenuBook ,
                        contentDescription = "Movie Image Icon",
                        tint = MaterialTheme.colors.primary
                    )
                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = "Order details",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.titleColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                IconButton(
                    onClick = { expanded = !expanded }
                ) {
                    Icon(
                        imageVector = if(expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = "Expand More Icon",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(bottom = SMALL_PADDING)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Order number",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.descriptionColor,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "#ASDF-#ASDF",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Divider(modifier = Modifier.padding(horizontal = 4.dp))
            Row(
                modifier = Modifier
                    .padding(top = LARGE_PADDING)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total:",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.titleColor,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "PEN 12.75",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.titleColor,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TrackScreenPreview() {
    TrackScreen(rememberNavController())
}