package com.manriquetavi.bakeryapp.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.model.User
import com.manriquetavi.bakeryapp.domain.repository.FirebaseAuthSource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Singleton

@Singleton
class FirebaseAuthSourceImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
): FirebaseAuthSource {
    var wasOperationSuccessful: Boolean = false

    override fun isUserAuthenticated(): Boolean = auth.currentUser != null
    override suspend fun getAuthState(): Flow<Boolean> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener {
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose{
            auth.removeAuthStateListener(authStateListener)
        }
    }

    override suspend fun firebaseAuthSignInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Response<Boolean>> = flow {
        wasOperationSuccessful = false
        try {
            emit(Response.Loading)
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                wasOperationSuccessful = true
            }.await()
            emit(Response.Success(wasOperationSuccessful))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

    override suspend fun signUp(
        username: String,
        email: String,
        password: String,
        phoneNumber: String
    ): Flow<Response<Boolean>> = flow {
        wasOperationSuccessful = false
        try {
            emit(Response.Loading)
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                wasOperationSuccessful = true
            }.await()
            if(wasOperationSuccessful) {
                val uid = auth.currentUser?.uid!!
                val user = User(
                    uid = uid,
                    username = username,
                    email = email,
                    password = password,
                    phoneNumber = phoneNumber
                )
                firestore.collection("users").document(uid).set(user).addOnSuccessListener {

                }.await()
            }
            emit(Response.Success(wasOperationSuccessful))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

    override suspend fun firebaseAuthSignOut(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            /*
            auth.signOut().apply {
                emit(Response.Success(true))
            }*/
            auth.signOut()
            emit(Response.Success(true))
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
