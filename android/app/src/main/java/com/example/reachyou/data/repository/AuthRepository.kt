package com.example.reachyou.data.repository

import android.net.Uri
import com.example.reachyou.data.local.SharedPreferenceManager
import com.example.reachyou.data.remote.retrofit.ApiService
import com.example.reachyou.model.UserModel
import com.example.reachyou.ui.utils.UiState
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import kotlin.Exception

class AuthRepository(private val apiService: ApiService, private val sharedPreferenceManager: SharedPreferenceManager) {
    fun login(username: String, password: String) = flow{
        emit(UiState.Loading)
        try {
            val client = apiService.loginUser(username, password)
            if(client.isSuccessful && client.body() != null){
                val responseBody = client.body()
                val user = responseBody?.let { UserModel(username, it.email, it.uuid, it.point, it.url) }
                emit(UiState.Success(user as UserModel))
            }
            else{
                emit(UiState.Error("Gagal login, pesan: ${client.errorBody()}"))
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
                val user = UserModel(id = responseBody!!.uuid, username = "", email = "", koin = 0, profileUrl = "")
                sharedPreferenceManager.saveUser(user)
                emit(UiState.Success(responseBody.msg))
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
            val user = sharedPreferenceManager.getUser()!!.id
            val client = apiService.setupProfile(user, profilePicture, username)
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

    fun updateProfile(type: String, value: String) = flow{
        emit(UiState.Loading)
        val user = sharedPreferenceManager.getUser()
        try {
            if(type == "Username"){
                val client = apiService.updateUsername(uuid = user!!.id, username = value)
                if(client.isSuccessful && client.body() != null){
                    val responseBody = client.body()
                    emit(UiState.Success(responseBody!!.msg))
                }
                else{
                    emit(UiState.Error(client.errorBody().toString()))
                }
            }
            else if(type == "Email"){
                val client = apiService.updateEmail(uuid = user!!.id, email = value)
                if(client.isSuccessful && client.body() != null){
                    val responseBody = client.body()
                    emit(UiState.Success(responseBody!!.msg))
                }
                else{
                    emit(UiState.Error(client.errorBody().toString()))
                }
            }
        } catch (e: Exception){
            emit(UiState.Error(e.message.toString()))
        }
    }

    fun laporBug(gambar: MultipartBody.Part, email: RequestBody, bug: RequestBody) = flow{
        emit(UiState.Loading)
        try {
            val client = apiService.laporBug(gambar, email, bug)
            if(client.isSuccessful && client.body() != null){
                val responseBody = client.body()
                emit(UiState.Success(responseBody!!.msg))
            }
            else{
                emit(UiState.Error(client.errorBody().toString()))
            }
        } catch (e: Exception){
            emit(UiState.Error(e.message.toString()))
        }
    }

    fun updateProfilePicture(file: MultipartBody.Part) = flow{
        emit(UiState.Loading)
        try {
            val user = sharedPreferenceManager.getUser()
            val client = apiService.updateProfilePicture(id = user!!.id, file = file)
            if(client.isSuccessful && client.body() != null){
                val responseBody = client.body()
                user.profileUrl = responseBody!!.url
                sharedPreferenceManager.saveUser(user)
                emit(UiState.Success(responseBody.msg))
            } else {
                emit(UiState.Error(client.errorBody().toString()))
            }
        } catch (e: Exception){
            emit(UiState.Error(e.message.toString()))
        }
    }

    companion object{
        @Volatile
        private var instance: AuthRepository? = null

        fun getInstance(
            apiService: ApiService,
            sharedPreferenceManager: SharedPreferenceManager
        ): AuthRepository =
            instance ?: synchronized(this){
                instance ?: AuthRepository(apiService, sharedPreferenceManager)
            }.also { instance = it }
    }
}