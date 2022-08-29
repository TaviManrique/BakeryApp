package com.manriquetavi.bakeryapp.presentation.screens.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.domain.model.User
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.UseCasesAuthentication
import com.manriquetavi.bakeryapp.domain.use_cases.data_store.on_boarding_page.UseCasesDataStore
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.UseCasesFirestore
import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.UseCasesLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    auth: FirebaseAuth,
    private val useCasesAuthentication: UseCasesAuthentication,
    private val useCasesFirestore: UseCasesFirestore,
    private val useCasesDataStore: UseCasesDataStore,
    private val useCasesLocalDataSource: UseCasesLocalDataSource
): ViewModel() {

    private val uid = auth.currentUser?.uid

    private val _responseUserDetails = mutableStateOf<Response<User?>>(Response.Success(null))
    val responseUserDetails: State<Response<User?>> = _responseUserDetails

    private val _signOutState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutState: State<Response<Boolean>> = _signOutState

    private val _imageProfileUri: MutableStateFlow<String> = MutableStateFlow("")
    val imageProfileUri: StateFlow<String> = _imageProfileUri

    init {
        getUserDetails()
        readImageProfile()
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

    private fun readImageProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            _imageProfileUri.value = useCasesDataStore.readImageProfileUseCase().stateIn(viewModelScope).value
        }
    }

    fun saveImageProfile(imageProfile: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCasesDataStore.saveImageProfileUseCase(imageProfile = imageProfile)
            _imageProfileUri.value = useCasesDataStore.readImageProfileUseCase().stateIn(viewModelScope).value
        }
    }

    fun deleteAllFoodCart() {
        viewModelScope.launch { useCasesLocalDataSource.deleteAllFoodsCart() }
    }
}