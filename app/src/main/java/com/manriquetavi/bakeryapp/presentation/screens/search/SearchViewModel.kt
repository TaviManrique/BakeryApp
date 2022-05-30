package com.manriquetavi.bakeryapp.presentation.screens.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manriquetavi.bakeryapp.domain.model.Food
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.UseCasesFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCasesFirestore: UseCasesFirestore
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedFoods = mutableStateOf<Response<List<Food>?>>(Response.Loading)
    val searchedFoods: MutableState<Response<List<Food>?>> = _searchedFoods

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchFoods(query: String) {
        viewModelScope.launch {
            useCasesFirestore.searchFoods(name = query).collect {
                _searchedFoods.value = it
            }
        }
    }
}