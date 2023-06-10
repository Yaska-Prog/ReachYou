package com.example.reachyou.ui.screen.detailQuiz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reachyou.data.local.database.Question
import com.example.reachyou.data.repository.QuizRepository
import com.example.reachyou.model.UserModel
import com.example.reachyou.ui.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailQuizViewmodel(private val quizRepository: QuizRepository): ViewModel() {
    private var listQuiz: List<Question> = listOf()
    var currentQuestionIndex: Int = 0
    var totalCorrectAnswer: Int = 0

    private val _uiState = MutableStateFlow<UiState<Question>>(UiState.Idle)
    val uiState: StateFlow<UiState<Question>> = _uiState

    var isDialogShown by mutableStateOf(false)

    fun getListQuestion(type: Int){
        viewModelScope.launch {
            quizRepository.getQuiz(type)
                .collect{result ->
                    when(result) {
                        is UiState.Success -> {
                            listQuiz = result.data
                        }
                        is UiState.Loading -> {
                            _uiState.value = UiState.Loading
                        }
                        else -> {}
                    }
                }
        }
    }

    fun getQuestion(){
        _uiState.value = UiState.Success(listQuiz[currentQuestionIndex])
    }

    fun answerQuestion(answer: String){
        _uiState.value = UiState.Loading
        if(answer == listQuiz[currentQuestionIndex].jawaban_benar){
            totalCorrectAnswer++
        }
        if(currentQuestionIndex < listQuiz.size - 1){
            currentQuestionIndex++
        }
        else if(currentQuestionIndex >= listQuiz.size -1){
            isDialogShown = true
        }
    }
    fun onDismissDialog(){
        isDialogShown = false
    }
}