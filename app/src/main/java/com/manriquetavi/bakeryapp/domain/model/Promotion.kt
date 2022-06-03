package com.manriquetavi.bakeryapp.domain.model

import androidx.room.PrimaryKey

data class Promotion(
    @PrimaryKey(autoGenerate = false)
    val id: String? = "",
    val image: String? = "",
    val backgroundColor: Long? = 0,
    val header: String? = "",
    val title: String? = "",
    val subtitle: String? = ""
)
