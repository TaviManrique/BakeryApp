package com.manriquetavi.bakeryapp.domain.use_cases.authentication.sign_up

import com.manriquetavi.bakeryapp.data.repository.RepositoryAuthentication

class SignUp(
    private val repositoryAuthentication: RepositoryAuthentication
) {
    suspend operator fun invoke(
        username: String,
        email: String,
        password: String,
        phoneNumber: String
    ) = repositoryAuthentication.signUp(username = username, email = email, password = password, phoneNumber = phoneNumber)
}