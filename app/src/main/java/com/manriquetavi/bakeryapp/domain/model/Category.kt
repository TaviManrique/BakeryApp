package com.manriquetavi.bakeryapp.domain.model

import androidx.room.PrimaryKey

data class Category(
    @PrimaryKey(autoGenerate = false)
    val id: String? = "",
    val name: String? = "",
    val image: String? = "",
)
