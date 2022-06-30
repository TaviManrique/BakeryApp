package com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.minus_quantity

import com.manriquetavi.bakeryapp.data.repository.RepositoryLocalDataSource

class MinusQuantityFoodCart(
    private val repositoryLocalDataSource: RepositoryLocalDataSource
) {
    suspend operator fun invoke(foodCartId: Int) = repositoryLocalDataSource.minusQuantityFoodCart(foodCartId)
}