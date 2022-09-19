package com.manriquetavi.bakeryapp.presentation.screens.order

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.manriquetavi.bakeryapp.data.repository.RepositoryFirestore
import com.manriquetavi.bakeryapp.domain.model.Food
import com.manriquetavi.bakeryapp.domain.model.Order
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.UseCasesAuthentication
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.UseCasesFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    auth: FirebaseAuth,
    private val useCasesFirestore: UseCasesFirestore
): ViewModel() {

    private val uid = auth.currentUser?.uid
    private val _allOrders = mutableStateOf<Response<List<Order>?>>(Response.Loading)
    val allOrders: MutableState<Response<List<Order>?>> = _allOrders

    init {
        getAllOrderByUser()
    }

    private fun getAllOrderByUser() {
        viewModelScope.launch {
            uid?.let {
                useCasesFirestore.getAllOrderByUser(uid).collect {
                    _allOrders.value = it
                }
            }
        }
    }
}