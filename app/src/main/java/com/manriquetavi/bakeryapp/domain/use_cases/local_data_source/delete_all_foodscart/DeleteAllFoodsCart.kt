package com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.delete_all_foodscart

import com.manriquetavi.bakeryapp.data.repository.RepositoryLocalDataSource

class DeleteAllFoodsCart(
    private val repositoryLocalDataSource: RepositoryLocalDataSource
) {
    suspend operator fun invoke() = repositoryLocalDataSource.deleteAllFoodsCart()
}