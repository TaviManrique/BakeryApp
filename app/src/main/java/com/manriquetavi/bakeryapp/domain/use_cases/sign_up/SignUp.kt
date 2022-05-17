package com.manriquetavi.bakeryapp.domain.use_cases.sign_up

import com.manriquetavi.bakeryapp.data.repository.Repository

class SignUp(
    private val repository: Repository
) {
    suspend operator fun invoke(
        username: String,
        email: String,
        password: String,
        phoneNumber: String
    ) = repository.signUp(username = username, email = email, password = password, phoneNumber = phoneNumber)
}