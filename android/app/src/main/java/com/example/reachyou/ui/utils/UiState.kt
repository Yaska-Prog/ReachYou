package com.example.reachyou.ui.utils

sealed class UiState<out T: Any?> {
    object Loading : UiState<Nothing>()

    data class Success<out T: Any>(val data: T) : UiState<T>()

    data class Error(val errorMessage: String) : UiState<Nothing>()

    object Idle: UiState<Nothing>()

    object Finish: UiState<Nothing>()
}