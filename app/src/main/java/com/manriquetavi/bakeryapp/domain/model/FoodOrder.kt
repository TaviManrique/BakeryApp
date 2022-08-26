package com.manriquetavi.bakeryapp.domain.model

import com.google.firebase.firestore.Exclude

data class FoodOrder(
    @get:Exclude var id: String = "",
    var name: String = "",
    var quantity: Int = 0,
    var unitPrice: String = ""
)
