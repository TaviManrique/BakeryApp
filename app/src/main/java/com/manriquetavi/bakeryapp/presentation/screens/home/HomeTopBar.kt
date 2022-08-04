package com.manriquetavi.bakeryapp.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.ui.theme.*

@Composable
fun HomeTopBar(
    screenNavController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.weight(0.8f),
            text = "What would you like to eat today?",
            maxLines = 2,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.titleColor,
            overflow = TextOverflow.Ellipsis
        )
        Icon(
            modifier = Modifier
                .clickable { screenNavController.navigate(Screen.Search.route) }
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colors.buttonBackgroundColor)
                .height(70.dp)
                .weight(0.2f),
            imageVector = Icons.Filled.Search,
            contentDescription = "Search Icon",
            tint = Color.White,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeTopBarPreview() {
    //HomeTopBar(screenNavController = rememberNavController())
}