package com.manriquetavi.bakeryapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_cart_table")
data class FoodCart(
    @PrimaryKey(autoGenerate = false) val id: String = "",
    val name: String? = "",
    val category: String? = "",
    val image: String? = "",
    val description: String? = "",
    val quantity: Int? = 0,
    val price: Double? = 0.00,
)
