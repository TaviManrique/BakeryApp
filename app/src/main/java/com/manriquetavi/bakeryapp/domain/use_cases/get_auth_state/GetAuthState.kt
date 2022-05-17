package com.manriquetavi.bakeryapp.domain.use_cases.get_auth_state

import com.manriquetavi.bakeryapp.data.repository.Repository

class GetAuthState(
    private val repository: Repository
) {
    operator fun invoke() = repository.getAuthState()
}