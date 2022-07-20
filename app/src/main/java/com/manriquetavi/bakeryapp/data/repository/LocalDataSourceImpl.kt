package com.manriquetavi.bakeryapp.data.repository

import androidx.lifecycle.asLiveData
import com.manriquetavi.bakeryapp.data.local.BakeryDatabase
import com.manriquetavi.bakeryapp.domain.model.FoodCart
import com.manriquetavi.bakeryapp.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl(
    bakeryDatabase: BakeryDatabase
): LocalDataSource {

    private val foodCartDao = bakeryDatabase.foodCartDao()
    override fun getAllFoodsCart(): Flow<List<FoodCart>> = foodCartDao.getAllFoodsCart()
    override suspend fun insertFoodCart(foodCart: FoodCart) = foodCartDao.insertFoodCart(foodCart)
    override suspend fun deleteAllFoodsCart() = foodCartDao.deleteAllFoodsCart()
    override suspend fun increaseQuantityFoodCart(foodCartId: String) = foodCartDao.increaseQuantityFoodCart(foodCartId)
    override suspend fun minusQuantityFoodCart(foodCartId: String) = foodCartDao.minusQuantityFoodCart(foodCartId)
    override suspend fun deleteFoodCart(foodCartId: String) = foodCartDao.deleteFoodCart(foodCartId)

}