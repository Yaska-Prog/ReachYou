package com.example.reachyou.ui.screen.detailQuiz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reachyou.data.local.SharedPreferenceManager
import com.example.reachyou.data.local.database.Question
import com.example.reachyou.data.repository.QuizRepository
import com.example.reachyou.model.UserModel
import com.example.reachyou.ui.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailQuizViewmodel(private val quizRepository: QuizRepository): ViewModel() {
    private var listQuiz: List<Question> = listOf()
    var currentQuestionIndex: Int = 0
    var totalCorrectAnswer: Int = 0
    private var coinMultiplier: Int = 0
    var coin: Int = 0

    private val _uiState = MutableStateFlow<UiState<Question>>(UiState.Idle)
    val uiState: StateFlow<UiState<Question>> = _uiState


    var isDialogShown by mutableStateOf(false)
    var isSuccess by mutableStateOf(false)
    var title by mutableStateOf("")
    var subtitle by mutableStateOf("")

    fun getListQuestion(type: Int){
        if(type == 1){
            coinMultiplier = 12
        }
        else if(type == 2){
            coinMultiplier = 20
        }
        else if(type == 3){
            coinMultiplier = 12
        }
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
        if(answer == listQuiz[currentQuestionIndex].jawaban_benar){
            totalCorrectAnswer++
            coin += coinMultiplier
        }
        if(currentQuestionIndex < listQuiz.size - 1){
            currentQuestionIndex++
            getQuestion()
        }
        else if(currentQuestionIndex >= listQuiz.size -1){
            _uiState.value = UiState.Finish
        }
    }
    fun finishQuiz(userId: String){
        viewModelScope.launch {
            quizRepository.finishQuiz(userId = userId, coin = coin)
                .onStart { _uiState.value = UiState.Loading }
                .collect{result ->
                    when(result){
                        is UiState.Success -> {
                            _uiState.value = UiState.Idle
                            isSuccess = true
                            title = "Sukses!"
                            subtitle = "Berhasil menyelesaikan quiz!"
                            isDialogShown = true
                        }
                        is UiState.Error -> {
                            isSuccess = false
                            isDialogShown = true
                            title = "Gagal!"
                            subtitle = "Gagal menyelesaikan quiz!"
                        }
                        UiState.Loading -> {
                            _uiState.value = UiState.Loading
                        }

                        else -> {}
                    }
                }
        }
    }
    fun onDismissDialog(){
        isDialogShown = false
    }
}