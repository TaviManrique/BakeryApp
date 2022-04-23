package com.manriquetavi.bakeryapp.domain.use_cases

import com.manriquetavi.bakeryapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.sign_in_email_password.SignInWithEmailAndPassword
import com.manriquetavi.bakeryapp.domain.use_cases.sign_out.SignOut

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val signInWithEmailAndPassword: SignInWithEmailAndPassword,
    val signOut: SignOut
)
