package com.manriquetavi.bakeryapp.presentation.screens.details

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manriquetavi.bakeryapp.domain.model.Food
import com.manriquetavi.bakeryapp.domain.model.FoodCart
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.UseCasesFirestore
import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.UseCasesLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCasesFirestore: UseCasesFirestore,
    private val savedStateHandle: SavedStateHandle,
    private val useCasesLocalDataSource: UseCasesLocalDataSource
): ViewModel() {

    private val _selectedFood: MutableState<Response<Food?>> = mutableStateOf(Response.Loading)
    val selectedFood: State<Response<Food?>> = _selectedFood

    var openDialog = MutableLiveData<Boolean>()
    var showToast = MutableLiveData<Boolean>()

    //private val _open: MutableStateFlow<Boolean> = MutableStateFlow(false)
    //val open: StateFlow<Boolean> = _open

    init {
        getFoodSelected()
    }
    private fun getFoodSelected() {
        viewModelScope.launch {
            val foodId = savedStateHandle.get<String>("foodId")
            foodId?.let {
                useCasesFirestore.getSelectedFood(foodId).collect {
                    _selectedFood.value = it
                }
            }
        }
        Log.d("DetailsViewModel", "getStoreSelected: ${_selectedFood.value}")
    }

    fun insertFoodCart(foodCart: FoodCart) {
        viewModelScope.launch {
            openDialog.value = true
            useCasesLocalDataSource.insertFoodCart(foodCart)
            withContext(Dispatchers.Default) {
                delay(2000)
            }
            openDialog.value = false
            showToast.value = true
        }
    }

    fun startThread() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                delay(1000)
            }
            showToast.value = true
            openDialog.value = false
        }
    }
}