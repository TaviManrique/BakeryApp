package com.manriquetavi.bakeryapp.domain.use_cases.on_boarding_page.save_onboarding

import com.manriquetavi.bakeryapp.data.repository.RepositoryAuthentication
import com.manriquetavi.bakeryapp.data.repository.RepositoryOnBoardingPage

class SaveOnBoardingUseCase(
    private val repositoryOnBoardingPage: RepositoryOnBoardingPage
) {
    suspend operator fun invoke(completed: Boolean) {
        repositoryOnBoardingPage.saveOnBoardingState(completed = completed)
    }
}