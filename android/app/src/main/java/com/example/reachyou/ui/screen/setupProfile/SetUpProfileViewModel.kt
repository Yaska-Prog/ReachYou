package com.example.reachyou.ui.screen.setupProfile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reachyou.data.repository.AuthRepository
import com.example.reachyou.ui.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class SetUpProfileViewModel(private val authRepository: AuthRepository): ViewModel() {
    private val _uiState = MutableStateFlow<UiState<String>>(UiState.Idle)
    val uiState: StateFlow<UiState<String>> = _uiState

    fun setupProfile(username: RequestBody, profilePicture: MultipartBody.Part){
        viewModelScope.launch {
            authRepository.setUpProfile(username, profilePicture)
                .onStart { _uiState.value = UiState.Loading }
                .collect{result -> _uiState.value = result as UiState<String>}
        }
    }
    fun updateUiState(){
        _uiState.value = UiState.Idle
    }
}