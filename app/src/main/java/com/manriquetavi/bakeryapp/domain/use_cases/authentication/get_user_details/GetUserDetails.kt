package com.manriquetavi.bakeryapp.domain.use_cases.authentication.get_user_details

import com.manriquetavi.bakeryapp.data.repository.RepositoryAuthentication

class GetUserDetails(
    private val repositoryAuthentication: RepositoryAuthentication
) {
    suspend operator fun invoke(uid: String) = repositoryAuthentication.getUserDetails(uid)
}