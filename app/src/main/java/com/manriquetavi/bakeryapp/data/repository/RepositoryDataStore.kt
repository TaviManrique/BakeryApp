package com.manriquetavi.bakeryapp.data.repository

import com.manriquetavi.bakeryapp.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryDataStore
@Inject constructor(
    private val dataStore: DataStoreOperations
) {
    //DataStore
    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }
    fun readOnBoardingState(): Flow<Boolean> = dataStore.readOnBoardingState()

    suspend fun saveImageProfile(imageProfile: String) {
        dataStore.saveImageProfile(imageProfile = imageProfile)
    }
    fun readImageProfile(): Flow<String> = dataStore.readImageProfile()
}