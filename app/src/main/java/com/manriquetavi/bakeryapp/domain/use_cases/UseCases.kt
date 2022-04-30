package com.manriquetavi.bakeryapp.domain.use_cases

import com.manriquetavi.bakeryapp.domain.use_cases.get_user.GetUser
import com.manriquetavi.bakeryapp.domain.use_cases.is_user_authenticated.IsUserAuthenticated
import com.manriquetavi.bakeryapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.sign_in_credential.SignInWithCredential
import com.manriquetavi.bakeryapp.domain.use_cases.sign_in_email_password.SignInWithEmailAndPassword
import com.manriquetavi.bakeryapp.domain.use_cases.sign_out.SignOut

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val signInWithEmailAndPassword: SignInWithEmailAndPassword,
    val signInWithCredential: SignInWithCredential,
    val signOut: SignOut,
    val isUserAuthenticated: IsUserAuthenticated,
    val getUser: GetUser
)
