package com.manriquetavi.bakeryapp.domain.use_cases.data_store.image_profile

import com.manriquetavi.bakeryapp.data.repository.RepositoryDataStore
import kotlinx.coroutines.flow.Flow

class ReadImageProfileUseCase(
    private val repositoryDataStore: RepositoryDataStore
) {
    operator fun invoke(): Flow<String> = repositoryDataStore.readImageProfile()
}