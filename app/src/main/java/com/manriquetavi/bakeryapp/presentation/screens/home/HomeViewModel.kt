package com.manriquetavi.bakeryapp.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manriquetavi.bakeryapp.domain.model.Category
import com.manriquetavi.bakeryapp.domain.model.Food
import com.manriquetavi.bakeryapp.domain.model.Promotion
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.UseCasesFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCasesFirestore: UseCasesFirestore
): ViewModel() {

    private val _allCategories = mutableStateOf<Response<List<Category>?>>(Response.Loading)
    val allCategories: State<Response<List<Category>?>> = _allCategories

    private val _allPromotions = mutableStateOf<Response<List<Promotion>?>>(Response.Loading)
    val allPromotions: State<Response<List<Promotion>?>> = _allPromotions

    private val _allRecommendations = mutableStateOf<Response<List<Food>?>>(Response.Loading)
    val allRecommendations: State<Response<List<Food>?>> = _allRecommendations

    init {
        getAllPromotions()
        getAllCategories()
        getRecommendations()
    }
    private fun getAllCategories() {
        viewModelScope.launch {
            useCasesFirestore.getAllCategories().collect {
                _allCategories.value = it
            }
        }
    }

    private fun getAllPromotions() {
        viewModelScope.launch {
            useCasesFirestore.getAllPromotions().collect {
                _allPromotions.value = it
            }
        }
    }

    private fun getRecommendations() {
        viewModelScope.launch {
            useCasesFirestore.getRecommendations().collect {
                _allRecommendations.value = it
            }
        }
    }
}