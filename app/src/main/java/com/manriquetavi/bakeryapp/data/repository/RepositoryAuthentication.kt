package com.manriquetavi.bakeryapp.data.repository

import com.google.firebase.auth.AuthCredential
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.repository.DataStoreOperations
import com.manriquetavi.bakeryapp.domain.repository.FirebaseAuthSource
import com.manriquetavi.bakeryapp.domain.repository.FirestoreDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryAuthentication
@Inject constructor(
    private val firebaseAuth: FirebaseAuthSource,
    private val firestore: FirestoreDataSource
) {

    //Firebase Auth
    suspend fun firebaseAuthSignInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Response<Boolean>> = firebaseAuth.firebaseAuthSignInWithEmailAndPassword(email = email, password = password)

    suspend fun firebaseAuthSignOut(): Flow<Response<Boolean>> = firebaseAuth.firebaseAuthSignOut()

    suspend fun signInWithCredential(authCredential: AuthCredential): Flow<Response<Boolean>> = firebaseAuth.signInWithCredential(authCredential)

    fun isUserAuthenticated(): Boolean = firebaseAuth.isUserAuthenticated()

    suspend fun getUserDetails(uid: String) = firestore.getUserDetails(uid)

    suspend fun getAuthState(): Flow<Boolean> = firebaseAuth.getAuthState()

    suspend fun signUp(
        username: String,
        email: String,
        password: String,
        phoneNumber: String
    ): Flow<Response<Boolean>> = firebaseAuth.signUp(username = username, email = email, password = password, phoneNumber = phoneNumber)
}