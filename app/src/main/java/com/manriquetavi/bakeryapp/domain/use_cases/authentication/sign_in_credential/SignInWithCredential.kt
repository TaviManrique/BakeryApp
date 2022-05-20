package com.manriquetavi.bakeryapp.domain.use_cases.authentication.sign_in_credential

import com.google.firebase.auth.AuthCredential
import com.manriquetavi.bakeryapp.data.repository.RepositoryAuthentication

class SignInWithCredential(
    private val repositoryAuthentication: RepositoryAuthentication
) {
    suspend operator fun invoke(authCredential: AuthCredential) = repositoryAuthentication.signInWithCredential(authCredential)
}