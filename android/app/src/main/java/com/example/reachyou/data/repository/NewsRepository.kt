package com.example.reachyou.data.repository

import com.example.reachyou.data.local.Article
import com.example.reachyou.data.local.SharedPreferenceManager
import com.example.reachyou.data.remote.retrofit.ApiService
import com.example.reachyou.ui.utils.UiState
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import kotlin.Exception

class NewsRepository(
    private val apiService: ApiService,
    private val sharedPreferenceManager: SharedPreferenceManager
) {
    fun getAllNews() = flow{
        emit(UiState.Loading)
        try {
            val client = apiService.getAllArticle()
            if(client.isSuccessful){
                val responseBody = client.body()
                val listArticle = responseBody!!.map { article ->
                    Article(
                        id = article.id,
                        uuid =  article.uuid,
                        urlGambar = article.url,
                        title = article.title,
                        description = article.description,
                        createdAt = article.createdAt,
                        username = article.username,
                        profil = article.profil
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
                    id = responseBody!!.id,
                    uuid =  responseBody.uuid,
                    urlGambar = responseBody.url,
                    title = responseBody.title,
                    description = responseBody.description,
                    createdAt = responseBody.createdAt,
                    username = responseBody.username,
                    profil = responseBody.profil
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

    fun createNews(file: MultipartBody.Part, title: RequestBody, description: RequestBody) = flow{
        emit(UiState.Loading)
        try {
            val user = sharedPreferenceManager.getUser()
            val client = apiService.createNews(user!!.id, file, title, description)
            if(client.isSuccessful && client.body() != null){
                val responseBody = client.body()
                emit(UiState.Success(responseBody!!.msg))
            }
            else{
                emit(UiState.Error("Client error"))
            }
        } catch (e: Exception){
            emit(UiState.Error("pesan kegagalan: ${e.message}"))
        }
    }

    companion object{
        @Volatile
        private var instance: NewsRepository? = null
        fun getInstance(
            apiService: ApiService,
            sharedPreferenceManager: SharedPreferenceManager
        ): NewsRepository =
            NewsRepository.instance ?: synchronized(this){
                NewsRepository.instance ?: NewsRepository(apiService, sharedPreferenceManager)
            }.also { NewsRepository.instance = it }
    }
}