package com.manriquetavi.bakeryapp.domain.use_cases.local_data_source

import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.delete_all_foodscart.DeleteAllFoodsCart
import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.delete_foodcart.DeleteFoodCart
import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.get_all_foodscart.GetAllFoodsCart
import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.increase_quantity.IncreaseQuantityFoodCart
import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.insert_foodcart.InsertFoodCart
import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.minus_quantity.MinusQuantityFoodCart

data class UseCasesLocalDataSource(
    val getAllFoodsCart: GetAllFoodsCart,
    val insertFoodCart: InsertFoodCart,
    val deleteAllFoodsCart: DeleteAllFoodsCart,
    val deleteFoodCart: DeleteFoodCart,
    val increaseQuantityFoodCart: IncreaseQuantityFoodCart,
    val minusQuantityFoodCart: MinusQuantityFoodCart
)