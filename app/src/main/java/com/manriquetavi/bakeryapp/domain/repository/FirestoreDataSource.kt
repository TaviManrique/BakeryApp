package com.manriquetavi.bakeryapp.domain.repository

import com.manriquetavi.bakeryapp.domain.model.Food
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface FirestoreDataSource {

    suspend fun getUserDetails(uid: String): Flow<Response<User>>
    suspend fun searchFoods(name: String): Flow<Response<List<Food>?>>
    suspend fun getSelectedFood(foodId: String): Flow<Response<Food?>>

}