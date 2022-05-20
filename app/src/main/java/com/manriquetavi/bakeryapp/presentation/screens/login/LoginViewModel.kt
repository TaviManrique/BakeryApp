package com.manriquetavi.bakeryapp.presentation.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.UseCasesAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCasesAuthentication: UseCasesAuthentication
): ViewModel() {

    private val _signInState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signInState: State<Response<Boolean>> = _signInState

    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            useCasesAuthentication.signInWithEmailAndPassword(email,password).collect { response ->
                _signInState.value = response
            }
        }
    }

    fun signInWithCredential(authCredential: AuthCredential) {
        viewModelScope.launch {
            useCasesAuthentication.signInWithCredential(authCredential).collect { response ->
                _signInState.value = response
            }
        }
    }
}