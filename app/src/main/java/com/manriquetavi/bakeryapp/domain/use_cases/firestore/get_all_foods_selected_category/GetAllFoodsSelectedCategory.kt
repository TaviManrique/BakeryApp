package com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_all_foods_selected_category

import com.manriquetavi.bakeryapp.data.repository.RepositoryFirestore

class GetAllFoodsSelectedCategory(
    private val repositoryFirestore: RepositoryFirestore
) {
    suspend operator fun invoke(category: String) = repositoryFirestore.getAllFoodsSelectedCategory(category)
}