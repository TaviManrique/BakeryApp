package com.manriquetavi.bakeryapp.domain.repository

import com.manriquetavi.bakeryapp.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthSource {
    suspend fun firebaseAuthSignInWithEmailAndPassword(email: String, password: String): Flow<Response<Boolean>>
    suspend fun firebaseAuthSignOut(): Flow<Response<Boolean>>
}