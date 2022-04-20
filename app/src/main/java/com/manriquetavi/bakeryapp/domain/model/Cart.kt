package com.manriquetavi.bakeryapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class Cart(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val foods: List<FoodOrder>? = emptyList()
)
