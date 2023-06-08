package com.example.reachyou.data.repository

import android.net.Uri
import com.example.reachyou.data.remote.retrofit.ApiService
import com.example.reachyou.model.UserModel
import com.example.reachyou.ui.utils.UiState
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
            else{
                emit(UiState.Error("Gagal melakukan register! Email sudah digunakan"))
            }
        }
        catch (e: Exception){
            emit(UiState.Error(e.message as String))
        }
    }
    fun setUpProfile(username: RequestBody, profilePicture: MultipartBody.Part) = flow{
        try {
            val client = apiService.setupProfile(profilePicture, username)
            if(client.isSuccessful && client.body() != null){
                val responseBody = client.body()
                emit(UiState.Success(responseBody!!.msg))
            }
            else{
                emit(UiState.Error("Gagal melakukan set up profile, pastikan data sesuai dan ukuran file lebih besar dari 5 Mb"))
            }
        }
        catch (e: Exception){
            emit(e.message?.let { UiState.Error(it) })
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