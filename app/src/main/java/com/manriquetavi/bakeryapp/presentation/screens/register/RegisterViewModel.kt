package com.manriquetavi.bakeryapp.presentation.screens.register

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
class RegisterViewModel @Inject constructor(
    private val useCasesAuthentication: UseCasesAuthentication
): ViewModel() {

    private val _signUpState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signUpState: State<Response<Boolean>> = _signUpState

    fun signUp(username: String, email: String, password: String, phoneNumber: String) {
        viewModelScope.launch {
            useCasesAuthentication.signUp(
                username = username,
                email = email,
                password = password,
                phoneNumber = phoneNumber
            ).collect {
                _signUpState.value = it
            }
        }
    }
}