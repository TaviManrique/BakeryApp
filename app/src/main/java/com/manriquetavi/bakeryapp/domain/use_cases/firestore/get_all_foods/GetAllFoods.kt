package com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_all_foods

import com.manriquetavi.bakeryapp.data.repository.RepositoryFirestore

class GetAllFoods(
    private val repositoryFirestore: RepositoryFirestore
) {
    suspend operator fun invoke() = repositoryFirestore.getAllFoods()
}