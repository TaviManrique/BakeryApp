package com.manriquetavi.bakeryapp.domain.model

import androidx.room.PrimaryKey


data class Food(
    @PrimaryKey(autoGenerate = false)
    val id: String? = "",
    val name: String?,
    val category: String?,
    val price: String?,
    val description: String?,
    val image: String?,
    val imagesDetails: List<String>?,
    val onStock: Boolean?
)