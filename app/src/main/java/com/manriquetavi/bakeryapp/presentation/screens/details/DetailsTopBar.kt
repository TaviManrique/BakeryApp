package com.manriquetavi.bakeryapp.presentation.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.presentation.screens.search.SearchTopBar
import com.manriquetavi.bakeryapp.ui.theme.buttonBackgroundColor

@Composable
fun DetailsTopBar(
    screenNavController: NavHostController
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    modifier = Modifier.clickable { screenNavController.popBackStack() },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = MaterialTheme.colors.buttonBackgroundColor
                )

                Icon(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable {  },
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite Icon",
                    tint = MaterialTheme.colors.buttonBackgroundColor
                )
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}

@Preview(showBackground = true)
@Composable
fun DetailsTopBarPreview() {
    DetailsTopBar(screenNavController = rememberNavController())
}