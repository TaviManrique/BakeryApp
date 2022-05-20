package com.manriquetavi.bakeryapp.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.model.User
import com.manriquetavi.bakeryapp.domain.repository.FirestoreDataSource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirestoreDataSourceImpl(
    private val firestore: FirebaseFirestore
): FirestoreDataSource {
    override suspend fun getUserDetails(uid: String): Flow<Response<User>> = callbackFlow {
        val snapshotListener = firestore
            .collection("users")
            .document(uid).addSnapshotListener { snapshot, e ->
                val response =
                    if(snapshot!= null) {
                        val userDetails = snapshot.toObject(User::class.java)
                        Response.Success(userDetails!!)
                    } else {
                        Response.Error(e?.message ?: e.toString())
                    }
                trySend(response).isSuccess
        }
        awaitClose {
            snapshotListener.remove()
        }
    }
}