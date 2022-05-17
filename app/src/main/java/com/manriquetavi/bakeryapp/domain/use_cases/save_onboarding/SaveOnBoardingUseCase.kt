package com.manriquetavi.bakeryapp.domain.use_cases.save_onboarding

import com.manriquetavi.bakeryapp.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed = completed)
    }
}