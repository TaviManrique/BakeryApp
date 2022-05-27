package com.manriquetavi.bakeryapp.domain.use_cases.firestore.user

import com.manriquetavi.bakeryapp.data.repository.RepositoryFirestore

class GetUserDetails(
    private val repositoryFirestore: RepositoryFirestore
) {
    suspend operator fun invoke(uid: String) = repositoryFirestore.getUserDetails(uid)
}