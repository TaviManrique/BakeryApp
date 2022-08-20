package com.manriquetavi.bakeryapp.presentation.screens.checkout

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manriquetavi.bakeryapp.domain.model.FoodCart
import com.manriquetavi.bakeryapp.domain.model.Order
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.UseCasesFirestore
import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.UseCasesLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val useCasesLocalDataSource: UseCasesLocalDataSource,
    private val useCasesFirestore: UseCasesFirestore
): ViewModel()  {

    private val _foodCardList = MutableStateFlow<List<FoodCart>>(emptyList())
    val foodCardList: StateFlow<List<FoodCart>> = _foodCardList

    private val _addOrderState = mutableStateOf<Response<Void?>>(Response.Success(null))
    val addOrderState: State<Response<Void?>> = _addOrderState

    var wasButtonDonePressed = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            useCasesLocalDataSource.getAllFoodsCart().distinctUntilChanged().collect { listOfFoodCart ->
                _foodCardList.value = listOfFoodCart
            }
        }
    }
    fun deleteAllFoodCart() {
        viewModelScope.launch { useCasesLocalDataSource.deleteAllFoodsCart() }
    }
    fun addOrder(foodCarts: List<FoodCart>, address: String) {
        wasButtonDonePressed.value = true
        viewModelScope.launch {
            useCasesFirestore.addOrder(foodCarts, address).collect { response ->
                _addOrderState.value = response
            }
        }
    }
}