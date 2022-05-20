package com.manriquetavi.bakeryapp.data.repository

import com.manriquetavi.bakeryapp.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryOnBoardingPage
@Inject constructor(
    private val dataStore: DataStoreOperations
) {
    //DataStore
    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }
    fun readOnBoardingState(): Flow<Boolean> = dataStore.readOnBoardingState()
}