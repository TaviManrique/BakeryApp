package com.manriquetavi.bakeryapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
    suspend fun saveImageProfile(imageProfile: String)
    fun readImageProfile(): Flow<String>
}