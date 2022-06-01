package com.manriquetavi.bakeryapp.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manriquetavi.bakeryapp.domain.model.Category
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

    init {
        getAllCategories()
    }
    private fun getAllCategories() {
        viewModelScope.launch {
            useCasesFirestore.getAllCategories().collect {
                _allCategories.value = it
            }
        }
    }
}