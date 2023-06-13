package com.example.reachyou.ui.component.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reachyou.data.repository.AuthRepository
import com.example.reachyou.ui.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class BottomSheetViewModel(private val authRepository: AuthRepository): ViewModel() {
    private val _uiState = MutableStateFlow<UiState<String>>(UiState.Idle)
    val uiState: StateFlow<UiState<String>> = _uiState

    var isDialogShown by mutableStateOf(false)
    var isPositive by mutableStateOf(false)
    var title by mutableStateOf("")
    var subtitle by mutableStateOf("")
    fun updateProfile(type:String, value: String) {
        viewModelScope.launch {
            authRepository.updateProfile(type, value)
                .onStart { _uiState.value = UiState.Loading }
                .collect{result -> _uiState.value = result}
        }
    }

    fun dismissDialog(){
        isDialogShown = false
    }
    fun showDialog(){
        isDialogShown = true
    }
    fun updateUi(){
        _uiState.value = UiState.Idle
    }
}