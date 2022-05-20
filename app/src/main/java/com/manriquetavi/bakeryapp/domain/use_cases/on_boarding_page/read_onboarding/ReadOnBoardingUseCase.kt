package com.manriquetavi.bakeryapp.domain.use_cases.on_boarding_page.read_onboarding

import com.manriquetavi.bakeryapp.data.repository.RepositoryAuthentication
import com.manriquetavi.bakeryapp.data.repository.RepositoryOnBoardingPage
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repositoryOnBoardingPage: RepositoryOnBoardingPage
) {
    operator fun invoke(): Flow<Boolean> = repositoryOnBoardingPage.readOnBoardingState()

}