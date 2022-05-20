package com.manriquetavi.bakeryapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.UseCasesAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val useCasesAuthentication: UseCasesAuthentication
): ViewModel() {

    val isUserAuthenticated get() = useCasesAuthentication.isUserAuthenticated()

    private val _signInState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signInState: State<Response<Boolean>> = _signInState

    private val _signUpState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signUpState: State<Response<Boolean>> = _signUpState

    private val _signOutState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutState: State<Response<Boolean>> = _signOutState

    private val _authState = mutableStateOf<Boolean>(false)
    val authState: State<Boolean> = _authState

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

    fun signOut() {
        viewModelScope.launch {
            useCasesAuthentication.signOut().collect { response ->
                _signOutState.value = response
                if (response == Response.Success(true)) {
                    _signInState.value = Response.Success(false)
                }
            }
        }
    }

    fun getAuthState() {
        viewModelScope.launch {
            useCasesAuthentication.getAuthState().collect {
                _authState.value = it
            }
        }
    }
}