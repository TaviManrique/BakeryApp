package com.manriquetavi.bakeryapp.data.repository

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.manriquetavi.bakeryapp.domain.model.*
import com.manriquetavi.bakeryapp.domain.repository.FirestoreDataSource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Singleton

@Singleton
class FirestoreDataSourceImpl(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
): FirestoreDataSource {
    var wasOperationSuccessful: Boolean = false

    override suspend fun getUserDetails(uid: String): Flow<Response<User>> = callbackFlow {
        val snapshotListener = firestore
            .collection("users")
            .document(uid)
            .addSnapshotListener { snapshot, e ->
                val response =
                    if (snapshot != null) {
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
            .addSnapshotListener { snapshot, e ->
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

    override suspend fun getAllCategories(): Flow<Response<List<Category>?>> = callbackFlow {
        val snapshotListener = firestore
            .collection("categories")
            .orderBy("name")
            .addSnapshotListener { snapshot, e ->
                val response =
                    if (snapshot != null) {
                        val categories = snapshot.toObjects(Category::class.java)
                        Response.Success(categories)
                    } else {
                        Response.Error(e?.message ?: e.toString())
                    }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun getAllPromotions(): Flow<Response<List<Promotion>?>> = callbackFlow {
        val snapshotListener = firestore
            .collection("promotions")
            .addSnapshotListener { snapshot, e ->
                val response =
                    if (snapshot != null) {
                        val promotions = snapshot.toObjects(Promotion::class.java)
                        Response.Success(promotions)
                    } else {
                        Response.Error(e?.message ?: e.toString())
                    }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun getRecommendations(): Flow<Response<List<Food>?>> = callbackFlow {
        val snapshotListener = firestore
            .collection("foods")
            .orderBy("stars", Query.Direction.DESCENDING).limit(5)
            .addSnapshotListener { snapshot, e ->
                val response =
                    if (snapshot != null) {
                        val recommendations = snapshot.toObjects(Food::class.java)
                        Response.Success(recommendations)
                    } else {
                        Response.Error(e?.message ?: e.toString())
                    }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun getAllFoodsSelectedCategory(category: String): Flow<Response<List<Food>?>> = callbackFlow {
        val snapshotListener = firestore
            .collection("foods")
            .whereEqualTo("category", category)
            .orderBy("stars", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, e ->
                val response =
                    if (snapshot != null) {
                        val foods = snapshot.toObjects(Food::class.java)
                        Log.d("TAG", "DocumentSnapshot all foods selected category: ${snapshot.documents}")
                        Response.Success(foods)
                    } else {
                        Response.Error(e?.message ?: e.toString())
                    }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun getAllFoods(): Flow<Response<List<Food>?>> = callbackFlow {
        val snapshotListener = firestore
            .collection("foods")
            .addSnapshotListener { snapshot, e ->
                val response =
                    if (snapshot != null) {
                        val foods = snapshot.toObjects(Food::class.java)
                        Response.Success(foods)
                    } else {
                        Response.Error(e?.message ?: e.toString())
                    }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun addOrder(foodCarts: List<FoodCart>, address: String): Flow<Response<Void?>> = flow {
        try {
            emit(Response.Loading)
            val foods = hashMapOf<String, FoodOrder>()
            val clientId = auth.currentUser!!.uid
            var aux = 0.00
            foodCarts.forEach { foodCart ->
                foods[foodCart.id] = FoodOrder(
                    id = foodCart.id,
                    name = foodCart.name!!,
                    quantity = foodCart.quantity!!
                )
                foodCart.price?.let {
                    aux += foodCart.quantity!!.times(it)
                }
            }
            val totalPrice: Double = aux
            val order = Order(
                clientId = clientId,
                foods = foods,
                totalPrice = totalPrice,
                status = 1,
                address = address
            )
            val orderId = firestore.collection("orders").document().id
            val addition = firestore
                .collection("orders")
                .document(orderId).set(order)
                .addOnSuccessListener {

                }.await()
            emit(Response.Success(addition))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }
}