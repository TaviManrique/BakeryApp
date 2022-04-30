package com.manriquetavi.bakeryapp.domain.use_cases.sign_in_credential

import com.google.firebase.auth.AuthCredential
import com.manriquetavi.bakeryapp.data.repository.Repository

class SignInWithCredential(
    private val repository: Repository
) {
    suspend operator fun invoke(authCredential: AuthCredential) = repository.signInWithCredential(authCredential)
}