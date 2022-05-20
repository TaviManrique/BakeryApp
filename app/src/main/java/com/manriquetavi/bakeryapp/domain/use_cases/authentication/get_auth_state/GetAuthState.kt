package com.manriquetavi.bakeryapp.domain.use_cases.authentication.get_auth_state

import com.manriquetavi.bakeryapp.data.repository.RepositoryAuthentication

class GetAuthState(
    private val repositoryAuthentication: RepositoryAuthentication
) {
    suspend operator fun invoke() = repositoryAuthentication.getAuthState()
}