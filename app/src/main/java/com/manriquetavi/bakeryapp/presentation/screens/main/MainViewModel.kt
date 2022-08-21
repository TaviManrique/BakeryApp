package com.manriquetavi.bakeryapp.presentation.screens.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _selectedItem = mutableStateOf(0)
    val selectedItem: State<Int> = _selectedItem

    fun updateSelectedItem(position: Int) {
        _selectedItem.value = position
    }

    init {
        viewModelScope.launch {
            val aux = savedStateHandle.get<String>("itemPosition")
            aux?.let {
                _selectedItem.value = it.toInt()
            }
        }
    }
}