package com.example.reachyou.data.repository

import android.net.Uri
import com.example.reachyou.model.UserModel
import com.example.reachyou.ui.utils.UiState
import kotlinx.coroutines.flow.flow

class AuthRepository {
    fun login(username: String, password: String) = flow{
        if(username != "" && password != ""){
            if(username == "Yaska" && password == "Yaska123"){
                val email = "Samting@gmail.com"
                val id = "5515464"
                val user = UserModel(username, email, id)
                emit(UiState.Success(user))
            }
            else{
                emit(UiState.Error("Tidak berhasil melakukan login"))
            }
        }
        else{
            emit(UiState.Error("Tidak berhasil melakukan login"))
        }
    }
    fun register(email: String, password: String) = flow{
        emit(UiState.Loading)
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

        fun getInstance(): AuthRepository =
            instance ?: synchronized(this){
                instance ?: AuthRepository()
            }.also { instance = it }
    }
}