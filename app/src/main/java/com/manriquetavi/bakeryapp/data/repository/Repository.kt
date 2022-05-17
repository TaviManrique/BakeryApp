package com.manriquetavi.bakeryapp.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
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

    //DataStore
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

    suspend fun signInWithCredential(authCredential: AuthCredential): Flow<Response<Boolean>> = firebaseAuth.signInWithCredential(authCredential)

    fun isUserAuthenticated(): Boolean = firebaseAuth.isUserAuthenticated()

    fun getUser(): FirebaseUser? = firebaseAuth.getUser()

    fun getAuthState(): Flow<Boolean> = firebaseAuth.getAuthState()

    suspend fun signUp(
        username: String,
        email: String,
        password: String,
        phoneNumber: String
    ): Flow<Response<Boolean>> = firebaseAuth.signUp(username = username, email = email, password = password, phoneNumber = phoneNumber)
}