package com.manriquetavi.bakeryapp.domain.use_cases.authentication.sign_out

import com.manriquetavi.bakeryapp.data.repository.RepositoryAuthentication

class SignOut(
    private val repositoryAuthentication: RepositoryAuthentication
) {
    suspend operator fun invoke() = repositoryAuthentication.firebaseAuthSignOut()
}