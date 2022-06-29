package com.manriquetavi.bakeryapp.presentation.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manriquetavi.bakeryapp.domain.model.FoodCart
import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.UseCasesLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val useCasesLocalDataSource: UseCasesLocalDataSource
): ViewModel() {
    private val _foodCardList = MutableStateFlow<List<FoodCart>>(emptyList())
    val foodCardList: StateFlow<List<FoodCart>> = _foodCardList

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
    fun increaseQuantityFoodCart() {
        viewModelScope.launch { useCasesLocalDataSource.increaseQuantityFoodCart(11) }
    }
}