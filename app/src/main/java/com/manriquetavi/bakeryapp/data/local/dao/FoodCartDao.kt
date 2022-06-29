package com.manriquetavi.bakeryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manriquetavi.bakeryapp.domain.model.FoodCart
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodCartDao {

    @Query("SELECT * FROM food_cart_table ORDER BY id ASC")
    fun getAllFoodsCart(): Flow<List<FoodCart>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodCart(foodCart: FoodCart)

    @Query("DELETE FROM food_cart_table")
    suspend fun deleteAllFoodsCart()

}