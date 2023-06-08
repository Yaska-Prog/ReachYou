package com.example.reachyou.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reachyou.data.repository.AuthRepository
import com.example.reachyou.model.UserModel
import com.example.reachyou.ui.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<UserModel>>(UiState.Idle)
    val uiState: StateFlow<UiState<UserModel>> = _uiState

    fun login(username: String, password: String) {
        viewModelScope.launch{
            authRepository.login(username, password)
                .onStart { _uiState.value = UiState.Loading }
                .collect{ result -> _uiState.value = result as UiState<UserModel>}
        }
    }

    fun updateUiState(){
        _uiState.value = UiState.Idle
    }
}