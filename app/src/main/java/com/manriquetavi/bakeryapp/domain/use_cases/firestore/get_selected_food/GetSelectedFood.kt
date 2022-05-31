package com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_selected_food

import com.manriquetavi.bakeryapp.data.repository.RepositoryFirestore

class GetSelectedFood(
    private val repositoryFirestore: RepositoryFirestore
) {
    suspend operator fun invoke(foodId: String) = repositoryFirestore.getSelectedFood(foodId)
}