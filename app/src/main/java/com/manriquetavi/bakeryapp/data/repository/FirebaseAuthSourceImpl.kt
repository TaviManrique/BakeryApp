package com.manriquetavi.bakeryapp.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.repository.FirebaseAuthSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Singleton

@Singleton
class FirebaseAuthSourceImpl(
    private val auth: FirebaseAuth
): FirebaseAuthSource {

    override fun isUserAuthenticated() = auth.currentUser != null

    override fun getUser(): FirebaseUser? = auth.currentUser

    override suspend fun firebaseAuthSignInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            auth.signInWithEmailAndPassword(email, password).await()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

    override suspend fun firebaseAuthSignOut(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            auth.signOut().apply {
                emit(Response.Success(true))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

    override suspend fun signInWithCredential(authCredential: AuthCredential): Flow<Response<Boolean>> = flow {
        try{
            emit(Response.Loading)
            auth.signInWithCredential(authCredential).await()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }
}
