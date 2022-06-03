package com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_all_promotions

import com.manriquetavi.bakeryapp.data.repository.RepositoryFirestore

class GetAllPromotions(
    private val repositoryFirestore: RepositoryFirestore
) {
    suspend operator fun invoke() = repositoryFirestore.getAllPromotions()
}