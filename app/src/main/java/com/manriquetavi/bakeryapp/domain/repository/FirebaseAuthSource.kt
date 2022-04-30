package com.manriquetavi.bakeryapp.domain.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.manriquetavi.bakeryapp.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthSource {
    fun isUserAuthenticated(): Boolean
    suspend fun firebaseAuthSignInWithEmailAndPassword(email: String, password: String): Flow<Response<Boolean>>
    suspend fun firebaseAuthSignOut(): Flow<Response<Boolean>>
    suspend fun signInWithCredential(authCredential: AuthCredential): Flow<Response<Boolean>>
    fun getUser(): FirebaseUser?
}