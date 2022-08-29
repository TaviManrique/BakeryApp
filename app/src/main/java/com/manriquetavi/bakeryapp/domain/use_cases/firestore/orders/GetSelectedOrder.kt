package com.manriquetavi.bakeryapp.domain.use_cases.firestore.orders

import com.manriquetavi.bakeryapp.data.repository.RepositoryFirestore

class GetSelectedOrder(
    private val repositoryFirestore: RepositoryFirestore
) {
    operator fun invoke(orderId: String) = repositoryFirestore.getSelectedOrder(orderId)
}