package com.manriquetavi.bakeryapp.domain.use_cases.data_store.image_profile

import com.manriquetavi.bakeryapp.data.repository.RepositoryDataStore

class SaveImageProfileUseCase(
    private val repositoryDataStore: RepositoryDataStore
) {
    suspend operator fun invoke(imageProfile: String) {
        repositoryDataStore.saveImageProfile(imageProfile = imageProfile)
    }
}