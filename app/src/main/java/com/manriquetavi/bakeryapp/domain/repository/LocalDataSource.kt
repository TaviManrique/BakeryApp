package com.manriquetavi.bakeryapp.domain.repository


import com.manriquetavi.bakeryapp.domain.model.FoodCart
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getAllFoodsCart(): Flow<List<FoodCart>>
    suspend fun insertFoodCart(foodCart: FoodCart)
    suspend fun deleteAllFoodsCart()
    suspend fun increaseQuantityFoodCart(foodCartId: Int)
    suspend fun minusQuantityFoodCart(foodCartId: Int)
    suspend fun deleteFoodCart(foodCartId: Int)
}