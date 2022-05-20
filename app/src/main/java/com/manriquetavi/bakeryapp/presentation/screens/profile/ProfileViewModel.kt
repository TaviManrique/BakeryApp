package com.manriquetavi.bakeryapp.presentation.screens.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.UseCasesAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCasesAuthentication: UseCasesAuthentication
): ViewModel() {

    private val _signOutState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutState: State<Response<Boolean>> = _signOutState

    fun signOut() {
        viewModelScope.launch {
            useCasesAuthentication.signOut().collect { response ->
                _signOutState.value = response
            }
        }
    }


}