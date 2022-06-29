package com.manriquetavi.bakeryapp.presentation.screens.details

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manriquetavi.bakeryapp.domain.model.Food
import com.manriquetavi.bakeryapp.domain.model.FoodCart
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.UseCasesFirestore
import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.UseCasesLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCasesFirestore: UseCasesFirestore,
    private val savedStateHandle: SavedStateHandle,
    private val useCasesLocalDataSource: UseCasesLocalDataSource
): ViewModel() {
    private val _selectedFood: MutableState<Response<Food?>> = mutableStateOf(Response.Loading)
    val selectedFood: State<Response<Food?>> = _selectedFood

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
        viewModelScope.launch { useCasesLocalDataSource.insertFoodCart(foodCart) }
    }
}