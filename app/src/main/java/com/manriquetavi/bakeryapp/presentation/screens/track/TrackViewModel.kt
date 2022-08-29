package com.manriquetavi.bakeryapp.presentation.screens.track

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manriquetavi.bakeryapp.domain.model.Order
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.UseCasesFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCasesFirestore: UseCasesFirestore
): ViewModel() {

    private val _selectedOrder: MutableState<Response<Order?>> = mutableStateOf(Response.Loading)
    val selectedOrder: State<Response<Order?>> = _selectedOrder

    init {
        getSelectedOrder()
    }

    private fun getSelectedOrder() {
        viewModelScope.launch {
            val orderId = savedStateHandle.get<String>("orderId")
            orderId?.let {
                useCasesFirestore.getSelectedOrder(orderId).collect {
                    _selectedOrder.value = it
                }
            }
        }
        Log.d("DetailsViewModel", "getStoreSelected: ${_selectedOrder.value}")
    }
}