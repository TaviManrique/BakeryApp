package com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.delete_foodcart

import com.manriquetavi.bakeryapp.data.repository.RepositoryLocalDataSource

class DeleteFoodCart(
    private val repositoryLocalDataSource: RepositoryLocalDataSource
) {
    suspend operator fun invoke(foodCartId: String) = repositoryLocalDataSource.deleteFoodCart(foodCartId)
}