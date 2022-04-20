package com.manriquetavi.bakeryapp.domain.model

import androidx.room.PrimaryKey

data class Order(
    @PrimaryKey(autoGenerate = false)
    val id: String? = "",
    val clientId: String?,
    val foods: List<FoodOrder>?,
    val totalPrice: String?,
    val status: Int = 0,

)
