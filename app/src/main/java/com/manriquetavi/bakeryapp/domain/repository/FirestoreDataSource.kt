package com.manriquetavi.bakeryapp.domain.repository

import com.manriquetavi.bakeryapp.domain.model.*
import kotlinx.coroutines.flow.Flow

interface FirestoreDataSource {

    suspend fun getUserDetails(uid: String): Flow<Response<User>>
    suspend fun searchFoods(name: String): Flow<Response<List<Food>?>>
    suspend fun getSelectedFood(foodId: String): Flow<Response<Food?>>
    suspend fun getAllCategories(): Flow<Response<List<Category>?>>
    suspend fun getAllPromotions(): Flow<Response<List<Promotion>?>>
    suspend fun getRecommendations(): Flow<Response<List<Food>?>>
    suspend fun getAllFoodsSelectedCategory(category: String): Flow<Response<List<Food>?>>
    suspend fun getAllFoods(): Flow<Response<List<Food>?>>
    fun addOrder(orderId: String, foodCarts: List<FoodCart>, address: String, methodPayment: String): Flow<Response<Void?>>
    fun getAllOrderByUser(id: String): Flow<Response<List<Order>?>>
    fun getSelectedOrder(orderId: String): Flow<Response<Order?>>
}