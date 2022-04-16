package com.manriquetavi.bakeryapp.domain.use_cases.read_onboarding

import com.manriquetavi.bakeryapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}