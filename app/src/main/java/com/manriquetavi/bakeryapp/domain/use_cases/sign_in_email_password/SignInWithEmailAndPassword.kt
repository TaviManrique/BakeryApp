package com.manriquetavi.bakeryapp.domain.use_cases.sign_in_email_password

import com.manriquetavi.bakeryapp.data.repository.Repository

class SignInWithEmailAndPassword(
    private val repository: Repository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ) = repository.firebaseAuthSignInWithEmailAndPassword(email = email, password = password)
}