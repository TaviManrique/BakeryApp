package com.manriquetavi.bakeryapp.domain.use_cases.authentication

import com.manriquetavi.bakeryapp.domain.use_cases.authentication.get_auth_state.GetAuthState
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.get_user_details.GetUserDetails
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.is_user_authenticated.IsUserAuthenticated
import com.manriquetavi.bakeryapp.domain.use_cases.on_boarding_page.read_onboarding.ReadOnBoardingUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.on_boarding_page.save_onboarding.SaveOnBoardingUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.sign_in_credential.SignInWithCredential
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.sign_in_email_password.SignInWithEmailAndPassword
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.sign_out.SignOut
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.sign_up.SignUp

data class UseCasesAuthentication(
    val signInWithEmailAndPassword: SignInWithEmailAndPassword,
    val signInWithCredential: SignInWithCredential,
    val signOut: SignOut,
    val isUserAuthenticated: IsUserAuthenticated,
    val getUserDetails: GetUserDetails,
    val signUp: SignUp,
    val getAuthState: GetAuthState
)
