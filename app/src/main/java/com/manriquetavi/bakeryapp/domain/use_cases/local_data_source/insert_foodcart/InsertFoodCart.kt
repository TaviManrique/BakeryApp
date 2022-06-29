package com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.insert_foodcart

import com.manriquetavi.bakeryapp.data.repository.RepositoryLocalDataSource
import com.manriquetavi.bakeryapp.domain.model.FoodCart

class InsertFoodCart(
    private val repositoryLocalDataSource: RepositoryLocalDataSource
) {
    suspend operator fun invoke(foodCart: FoodCart) = repositoryLocalDataSource.insertFoodCart(foodCart)
}