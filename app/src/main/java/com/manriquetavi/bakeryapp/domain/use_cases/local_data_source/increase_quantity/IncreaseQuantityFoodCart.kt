package com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.increase_quantity

import com.manriquetavi.bakeryapp.data.repository.RepositoryLocalDataSource

class IncreaseQuantityFoodCart (
    private val repositoryLocalDataSource: RepositoryLocalDataSource
) {
    suspend operator fun invoke(foodCartId: Int) = repositoryLocalDataSource.increaseQuantityFoodCart(foodCartId)
}