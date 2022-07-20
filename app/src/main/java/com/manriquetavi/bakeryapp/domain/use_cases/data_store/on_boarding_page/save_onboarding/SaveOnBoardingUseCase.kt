package com.manriquetavi.bakeryapp.domain.use_cases.data_store.on_boarding_page.save_onboarding

import com.manriquetavi.bakeryapp.data.repository.RepositoryDataStore

class SaveOnBoardingUseCase(
    private val repositoryDataStore: RepositoryDataStore
) {
    suspend operator fun invoke(completed: Boolean) {
        repositoryDataStore.saveOnBoardingState(completed = completed)
    }
}