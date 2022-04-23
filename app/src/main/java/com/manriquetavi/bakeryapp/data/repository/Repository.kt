package com.manriquetavi.bakeryapp.data.repository

import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.repository.DataStoreOperations
import com.manriquetavi.bakeryapp.domain.repository.FirebaseAuthSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository
@Inject constructor(
    private val dataStore: DataStoreOperations,
    private val firebaseAuth: FirebaseAuthSource
) {

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }
    fun readOnBoardingState(): Flow<Boolean> = dataStore.readOnBoardingState()

    //Firebase Auth
    suspend fun firebaseAuthSignInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Response<Boolean>> = firebaseAuth.firebaseAuthSignInWithEmailAndPassword(email = email, password = password)

    suspend fun firebaseAuthSignOut(): Flow<Response<Boolean>> = firebaseAuth.firebaseAuthSignOut()
}