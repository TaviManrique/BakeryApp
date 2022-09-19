package com.manriquetavi.bakeryapp.domain.model

import androidx.room.PrimaryKey
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class Order(
    @PrimaryKey(autoGenerate = false)
    val id: String? = "",
    var clientId: String = "",
    var foodOrders: Map<String, FoodOrder> = hashMapOf(),
    var totalPrice: Double = 0.0,
    var status: Int = 0,
    var address: String = "",
    @ServerTimestamp var date: Timestamp? = null,
    var methodPayment: String = ""
)
