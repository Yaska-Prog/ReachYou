package com.example.reachyou.data.repository

import com.example.reachyou.data.local.Article
import com.example.reachyou.data.remote.retrofit.ApiService
import com.example.reachyou.ui.utils.UiState
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class NewsRepository(
    private val apiService: ApiService
) {
    fun getAllNews() = flow{
        emit(UiState.Loading)
        try {
            val client = apiService.getAllArticle()
            if(client.isSuccessful){
                val responseBody = client.body()
                val listArticle = responseBody!!.map { article ->
                    Article(
                        article.id,
                        article.uuid,
                        article.image,
                        article.title,
                        article.description,
                        article.createdAt
                    )
                }
                emit(UiState.Success(listArticle))
            }
            else{
                emit(UiState.Error("Client Gagal"))
            }
        }
        catch (e: Exception){
            emit(UiState.Error("Retrofit gagal ${e.message}"))
        }
    }

    fun getDetailNews(id: Int) = flow {
        emit(UiState.Loading)
        try {
            val client = apiService.getDetailArticle(id)
            if(client.isSuccessful){
                val responseBody = client.body()
                val article = Article(
                    responseBody!!.id,
                    responseBody.uuid,
                    responseBody.image,
                    responseBody.title,
                    responseBody.description,
                    responseBody.createdAt
                )
                emit(UiState.Success(article))
            }
            else{
                emit(UiState.Error("Client gagal ${client.errorBody()}"))
            }
        } catch (e: Exception){
            emit(UiState.Error("Exception error, message: ${e.message}"))
        }
    }

    companion object{
        @Volatile
        private var instance: NewsRepository? = null
        fun getInstance(
            apiService: ApiService
        ): NewsRepository =
            NewsRepository.instance ?: synchronized(this){
                NewsRepository.instance ?: NewsRepository(apiService)
            }.also { NewsRepository.instance = it }
    }
}