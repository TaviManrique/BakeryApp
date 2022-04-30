package com.manriquetavi.bakeryapp.domain.use_cases.get_user

import com.manriquetavi.bakeryapp.data.repository.Repository

class GetUser(
    private val repository: Repository
) {
    operator fun invoke() = repository.getUser()
}