package com.example.reachyou.ui.screen.news

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reachyou.data.local.Article
import com.example.reachyou.data.repository.NewsRepository
import com.example.reachyou.ui.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository): ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Article>>> (UiState.Idle)
    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    var isDialogShown by mutableStateOf(false)

    fun getListArticle(){
        viewModelScope.launch {
            newsRepository.getAllNews().collect{result ->
                _uiState.value = result
            }
        }
    }
    fun showDialog(){
        isDialogShown = true
    }
    fun dismissDialog(){
        isDialogShown = false
        _uiState.value = UiState.Finish
    }
}