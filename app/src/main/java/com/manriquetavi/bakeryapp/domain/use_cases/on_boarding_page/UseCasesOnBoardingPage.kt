package com.manriquetavi.bakeryapp.domain.use_cases.on_boarding_page

import com.manriquetavi.bakeryapp.domain.use_cases.on_boarding_page.read_onboarding.ReadOnBoardingUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.on_boarding_page.save_onboarding.SaveOnBoardingUseCase

data class UseCasesOnBoardingPage (
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase
)