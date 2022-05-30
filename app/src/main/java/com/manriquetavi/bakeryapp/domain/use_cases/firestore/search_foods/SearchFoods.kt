package com.manriquetavi.bakeryapp.domain.use_cases.firestore.search_foods

import com.manriquetavi.bakeryapp.data.repository.RepositoryFirestore

class SearchFoods(
    private val repositoryFirestore: RepositoryFirestore
) {
    suspend operator fun invoke(name: String) = repositoryFirestore.searchFoods(name)
}