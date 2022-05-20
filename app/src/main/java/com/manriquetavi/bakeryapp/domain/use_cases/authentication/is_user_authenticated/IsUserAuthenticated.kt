package com.manriquetavi.bakeryapp.domain.use_cases.authentication.is_user_authenticated

import com.manriquetavi.bakeryapp.data.repository.RepositoryAuthentication

class IsUserAuthenticated(
    private val repositoryAuthentication: RepositoryAuthentication
) {
    operator fun invoke() = repositoryAuthentication.isUserAuthenticated()
}