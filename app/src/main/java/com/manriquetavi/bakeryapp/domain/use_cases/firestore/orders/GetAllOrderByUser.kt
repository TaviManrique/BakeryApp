package com.manriquetavi.bakeryapp.domain.use_cases.firestore.orders

import com.manriquetavi.bakeryapp.data.repository.RepositoryFirestore

class GetAllOrderByUser(
    private val repositoryFirestore: RepositoryFirestore
) {
    operator fun invoke(id: String) = repositoryFirestore.getAllOrderByUser(id)
}