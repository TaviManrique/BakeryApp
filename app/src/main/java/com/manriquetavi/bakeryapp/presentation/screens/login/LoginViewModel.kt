package com.manriquetavi.bakeryapp.presentation.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.model.User
import com.manriquetavi.bakeryapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _signInState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signInState: State<Response<Boolean>> = _signInState

    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            useCases.signInWithEmailAndPassword(email,password).collect { response ->
                _signInState.value = response
            }
        }
    }

    fun signInWithCredential(authCredential: AuthCredential) {
        viewModelScope.launch {
            useCases.signInWithCredential(authCredential).collect { response ->
                _signInState.value = response
            }
        }
    }
}