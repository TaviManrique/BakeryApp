package com.manriquetavi.bakeryapp.domain.use_cases.is_user_authenticated

import com.manriquetavi.bakeryapp.data.repository.Repository

class IsUserAuthenticated(
    private val repository: Repository
) {
    operator fun invoke() = repository.isUserAuthenticated()
}