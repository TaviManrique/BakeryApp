package com.manriquetavi.bakeryapp.data.repository

import com.manriquetavi.bakeryapp.domain.repository.FirestoreDataSource
import javax.inject.Inject

class RepositoryFirestore
@Inject constructor(
    private val firestore: FirestoreDataSource
) {

    suspend fun getUserDetails(uid: String) = firestore.getUserDetails(uid)
    suspend fun searchFoods(name: String) = firestore.searchFoods(name)
    suspend fun getSelectedFood(foodId: String) = firestore.getSelectedFood(foodId)
    suspend fun getAllCategories() = firestore.getAllCategories()
    suspend fun getAllPromotions() = firestore.getAllPromotions()
    suspend fun getRecommendations() = firestore.getRecommendations()
    suspend fun getAllFoodsSelectedCategory(category: String) = firestore.getAllFoodsSelectedCategory(category)

}