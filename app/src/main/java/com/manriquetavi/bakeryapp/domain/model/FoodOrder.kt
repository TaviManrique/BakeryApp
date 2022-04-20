package com.manriquetavi.bakeryapp.domain.model

import androidx.room.PrimaryKey

data class FoodOrder(
    @PrimaryKey(autoGenerate = false)
    val id: String? = "",
    val quantity: Int?,
    val image: String?,
    val price: String?
)
