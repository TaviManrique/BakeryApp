package com.manriquetavi.bakeryapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_cart_table")
data class FoodCart(
    @PrimaryKey(autoGenerate = false) var id: String = "",
    var name: String? = "",
    var category: String? = "",
    var image: String? = "",
    var description: String? = "",
    var quantity: Int? = 0,
    var price: Double? = 0.00,
)
