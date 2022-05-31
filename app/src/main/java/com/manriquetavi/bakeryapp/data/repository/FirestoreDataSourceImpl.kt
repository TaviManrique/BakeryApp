package com.manriquetavi.bakeryapp.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.manriquetavi.bakeryapp.domain.model.Food
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.model.User
import com.manriquetavi.bakeryapp.domain.repository.FirestoreDataSource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Singleton

@Singleton
class FirestoreDataSourceImpl(
    private val firestore: FirebaseFirestore
): FirestoreDataSource {

    override suspend fun getUserDetails(uid: String): Flow<Response<User>> = callbackFlow {
        val snapshotListener = firestore
            .collection("users")
            .document(uid)
            .addSnapshotListener { snapshot, e ->
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

    override suspend fun searchFoods(name: String): Flow<Response<List<Food>?>> = callbackFlow {
        val snapshotListener = firestore
            .collection("foods")
            .whereEqualTo("name", name)
            .addSnapshotListener { snapshot,e ->
                val response =
                    if (snapshot != null) {
                        val foodsFounded = snapshot.toObjects(Food::class.java)
                        Response.Success(foodsFounded)
                    } else {
                        Response.Error(e?.message ?: e.toString())
                    }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun getSelectedFood(foodId: String): Flow<Response<Food?>> = callbackFlow {
        val document = firestore
            .collection("foods")
            .document(foodId)
            .get()
            .addOnSuccessListener { document ->
                val response =
                    if (document != null) {
                        val food = document.toObject(Food::class.java)
                        Log.d("TAG", "DocumentSnapshot data food: ${document.data}")
                        Response.Success(food)
                    } else {
                        Response.Success(null)
                    }
                trySend(response).isSuccess
            }
            .addOnFailureListener { e ->
                trySend(Response.Error(e.message ?: e.toString())).isFailure
        }
        awaitClose {
            document.isCanceled
        }
    }
    /*
        val document = firestore
            .collection("foods")
            .document(foodId)
            .get()
            .addOnSuccessListener { document ->
                val response = if(document!=null) {
                    val food = document.toObject(Food::class.java)
                    Response.Success(food)
                } else {
                    Response.Error("No such document")
                }
                trySend(response).isSuccess
            }
            .addOnFailureListener { e ->
                trySend(Response.Error(e.message ?: e.toString())).isSuccess
            }
    }*/
}