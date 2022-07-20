package com.manriquetavi.bakeryapp.domain.use_cases.data_store.on_boarding_page.read_onboarding

import com.manriquetavi.bakeryapp.data.repository.RepositoryDataStore
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repositoryDataStore: RepositoryDataStore
) {
    operator fun invoke(): Flow<Boolean> = repositoryDataStore.readOnBoardingState()
}