package com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.get_all_foodscart

import com.manriquetavi.bakeryapp.data.repository.RepositoryLocalDataSource
import com.manriquetavi.bakeryapp.domain.model.FoodCart
import kotlinx.coroutines.flow.Flow

class GetAllFoodsCart(
    private val repositoryLocalDataSource: RepositoryLocalDataSource
) {
    operator fun invoke(): Flow<List<FoodCart>> = repositoryLocalDataSource.getAllFoodsCart()
}