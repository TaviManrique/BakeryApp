package com.manriquetavi.bakeryapp.presentation.screens.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.model.User
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.UseCasesAuthentication
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.UseCasesFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val useCasesAuthentication: UseCasesAuthentication,
    private val useCasesFirestore: UseCasesFirestore
): ViewModel() {

    private val uid = auth.currentUser?.uid

    private val _responseUserDetails = mutableStateOf<Response<User?>>(Response.Success(null))
    val responseUserDetails: State<Response<User?>> = _responseUserDetails

    private val _signOutState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutState: State<Response<Boolean>> = _signOutState

    init {
        getUserDetails()
    }
    private fun getUserDetails() {
        uid?.let { uid ->
            viewModelScope.launch {
                useCasesFirestore.getUserDetails(uid = uid).collect {
                    _responseUserDetails.value = it
                }
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            useCasesAuthentication.signOut().collect { response ->
                _signOutState.value = response
            }
        }
    }
}