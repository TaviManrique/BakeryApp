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

    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user

    private val _email = mutableStateOf("")
    val email = _email

    private val _password = mutableStateOf("")
    val password = _password

    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            useCases.signInWithEmailAndPassword(email,password).collect { response ->
                _signInState.value = response
            }
        }
    }

    suspend fun sigInWithGoogle(email: String, displayName: String) {
        delay(2000)
        _user.value = User(email, displayName)
    }

    fun signInWithCredential(authCredential: AuthCredential) {
        viewModelScope.launch {
            useCases.signInWithCredential(authCredential).collect { response ->
                _signInState.value = response
            }
        }
    }
}