package com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_recommendations

import com.manriquetavi.bakeryapp.data.repository.RepositoryFirestore

class GetRecommendations(
    private val repositoryFirestore: RepositoryFirestore
) {
    suspend operator fun invoke() = repositoryFirestore.getRecommendations()
}