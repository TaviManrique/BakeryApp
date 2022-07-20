package com.manriquetavi.bakeryapp.domain.use_cases.data_store.on_boarding_page

import com.manriquetavi.bakeryapp.domain.use_cases.data_store.image_profile.ReadImageProfileUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.data_store.image_profile.SaveImageProfileUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.data_store.on_boarding_page.read_onboarding.ReadOnBoardingUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.data_store.on_boarding_page.save_onboarding.SaveOnBoardingUseCase

data class UseCasesDataStore (
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val saveImageProfileUseCase: SaveImageProfileUseCase,
    val readImageProfileUseCase: ReadImageProfileUseCase
)