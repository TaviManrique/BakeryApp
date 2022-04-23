package com.manriquetavi.bakeryapp.domain.use_cases.sign_out

import com.manriquetavi.bakeryapp.data.repository.Repository

class SignOut(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.firebaseAuthSignOut()
}