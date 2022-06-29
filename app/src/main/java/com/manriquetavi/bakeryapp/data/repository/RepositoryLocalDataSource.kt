package com.manriquetavi.bakeryapp.data.repository

import com.manriquetavi.bakeryapp.domain.model.FoodCart
import com.manriquetavi.bakeryapp.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryLocalDataSource
@Inject constructor(
    private val local: LocalDataSource
) {
    fun getAllFoodsCart(): Flow<List<FoodCart>> = local.getAllFoodsCart()
    suspend fun insertFoodCart(foodCart: FoodCart) = local.insertFoodCart(foodCart)
    suspend fun deleteAllFoodsCart() = local.deleteAllFoodsCart()
}