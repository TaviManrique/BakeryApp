package com.manriquetavi.bakeryapp.domain.use_cases.firestore.orders

import com.manriquetavi.bakeryapp.data.repository.RepositoryFirestore
import com.manriquetavi.bakeryapp.domain.model.FoodCart
import com.manriquetavi.bakeryapp.domain.model.Order

class AddOrder(
    private val repositoryFirestore: RepositoryFirestore
) {
    operator fun invoke(orderId: String, foodCarts: List<FoodCart>, address: String, methodPayment: String) = repositoryFirestore.addOrder(orderId, foodCarts, address, methodPayment)
}