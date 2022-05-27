package com.manriquetavi.bakeryapp.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.UseCasesAuthentication
import com.manriquetavi.bakeryapp.domain.use_cases.on_boarding_page.UseCasesOnBoardingPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCasesAuthentication: UseCasesAuthentication,
    private val useCasesOnBoardingPage: UseCasesOnBoardingPage
): ViewModel() {

    val isUserAuthenticated get() = useCasesAuthentication.isUserAuthenticated()
    private val _onBoardingCompleted: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val onBoardingCompleted: StateFlow<Boolean> = _onBoardingCompleted
    init {
        viewModelScope.launch(Dispatchers.IO){
            _onBoardingCompleted.value = useCasesOnBoardingPage.readOnBoardingUseCase().stateIn(viewModelScope).value
        }
    }

    fun getAuthState() = liveData(Dispatchers.IO) {
        useCasesAuthentication.getAuthState().collect { response ->
            emit(response)
        }
    }
}