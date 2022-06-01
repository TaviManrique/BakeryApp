package com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_all_categories

import com.manriquetavi.bakeryapp.data.repository.RepositoryFirestore

class GetAllCategories(
    private val repositoryFirestore: RepositoryFirestore
) {
    suspend operator fun invoke() = repositoryFirestore.getAllCategories()
}