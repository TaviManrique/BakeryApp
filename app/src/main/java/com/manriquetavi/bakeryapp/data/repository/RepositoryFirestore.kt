package com.manriquetavi.bakeryapp.data.repository

import com.manriquetavi.bakeryapp.domain.model.FoodCart
import com.manriquetavi.bakeryapp.domain.model.Order
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.repository.FirestoreDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryFirestore
@Inject constructor(
    private val firestore: FirestoreDataSource
) {
    suspend fun getUserDetails(uid: String) = firestore.getUserDetails(uid)
    suspend fun searchFoods(name: String) = firestore.searchFoods(name)
    suspend fun getSelectedFood(foodId: String) = firestore.getSelectedFood(foodId)
    suspend fun getAllCategories() = firestore.getAllCategories()
    suspend fun getAllPromotions() = firestore.getAllPromotions()
    suspend fun getRecommendations() = firestore.getRecommendations()
    suspend fun getAllFoodsSelectedCategory(category: String) = firestore.getAllFoodsSelectedCategory(category)
    suspend fun getAllFoods() = firestore.getAllFoods()
    fun addOrder(orderId: String, foodCarts: List<FoodCart>, address: String, methodPayment: String): Flow<Response<Void?>> = firestore.addOrder(orderId, foodCarts, address, methodPayment)
    fun getAllOrderByUser(id: String): Flow<Response<List<Order>?>> = firestore.getAllOrderByUser(id)
    fun getSelectedOrder(orderId: String): Flow<Response<Order?>> = firestore.getSelectedOrder(orderId)
}