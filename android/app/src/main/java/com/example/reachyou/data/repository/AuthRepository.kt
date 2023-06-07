package com.example.reachyou.data.repository

import android.net.Uri
import com.example.reachyou.data.remote.retrofit.ApiService
import com.example.reachyou.model.UserModel
import com.example.reachyou.ui.utils.UiState
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class AuthRepository(private val apiService: ApiService) {
    fun login(username: String, password: String) = flow{
        emit(UiState.Loading)
        try {
            val client = apiService.loginUser(username, password)
            if(client.isSuccessful && client.body() != null){
                val responseBody = client.body()
                val user = responseBody?.let { UserModel(username, password, it.uuid) }
                emit(UiState.Success(user as UserModel))
            }
            else{
                emit(UiState.Error("Gagal register, data tidak valid"))
            }
        }
        catch (e: Exception){
            emit(e.message?.let { UiState.Error(it) })
        }
    }
    fun register(email: String, password: String) = flow{
        emit(UiState.Loading)
        try {
            val client = apiService.regisUser(email, password)
            if(client.isSuccessful && client.body() != null){
                val responseBody = client.body()
                emit(UiState.Success(responseBody!!.msg))
            }
        }
        catch (e: Exception){
            emit(UiState.Error(e.message as String))
        }
        if(email != "" && password != ""){
            emit(UiState.Success("Berhasil register"))
        }
        else{
            emit(UiState.Error("Gagal register, data tidak valid"))
        }
    }
    fun setUpProfile(username: String, profilePicture: Uri?) = flow{
        if(username != "" && profilePicture != null){
            emit(UiState.Success("Berhasil set up profile"))
        }
        else{
            emit(UiState.Error("Gagal register, data tidak valid"))
        }
    }

    companion object{
        @Volatile
        private var instance: AuthRepository? = null

        fun getInstance(
            apiService: ApiService
        ): AuthRepository =
            instance ?: synchronized(this){
                instance ?: AuthRepository(apiService)
            }.also { instance = it }
    }
}