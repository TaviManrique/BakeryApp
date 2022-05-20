package com.manriquetavi.bakeryapp.domain.use_cases.authentication.sign_in_email_password

import com.manriquetavi.bakeryapp.data.repository.RepositoryAuthentication

class SignInWithEmailAndPassword(
    private val repositoryAuthentication: RepositoryAuthentication
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ) = repositoryAuthentication.firebaseAuthSignInWithEmailAndPassword(email = email, password = password)
}