package com.example.reachyou.data.repository

import com.example.reachyou.data.local.database.QuestionDAO
import com.example.reachyou.data.remote.retrofit.ApiService
import com.example.reachyou.ui.utils.UiState
import kotlinx.coroutines.flow.flow

class QuizRepository(
    private val apiService: ApiService,
    private val questionDao: QuestionDAO,
    ) {

    fun getQuiz(type: Int)= flow {
        emit(UiState.Loading)
        try {
            val listQuestion = questionDao.getQuestionBasedOnType(type)
            if(listQuestion.isNotEmpty()){
                emit(UiState.Success(listQuestion))
            }
            else{
                emit(UiState.Error("Gagal mengambil data dari room!"))
            }
        } catch (e: Exception){
            emit(UiState.Error(e.message as String))
        }
    }

    fun finishQuiz(coin: Int, userId: String) = flow {
        emit(UiState.Loading)
        try {
            val client = apiService.updateCoin(userId, coin)
            if(client.isSuccessful){
                emit(UiState.Success("Berhasil melakukan update koin"))
            }
            else{
                emit(UiState.Error("Client gagal!"))
            }
        }
        catch (e: Exception){
            emit(UiState.Error(e.message.toString()))
        }
    }
    companion object{
        @Volatile
        private var instance: QuizRepository? = null

        fun getInstance(
            apiService: ApiService,
            questionDao: QuestionDAO
        ): QuizRepository =
            instance ?: synchronized(this){
                instance ?: QuizRepository(apiService, questionDao)
            }.also { instance = it }
    }
}