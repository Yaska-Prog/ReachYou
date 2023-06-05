package com.example.reachyou.data.repository

import com.example.reachyou.ui.utils.UiState
import kotlinx.coroutines.flow.flow

class AuthRepository {
    fun login(email: String, password: String) = flow{
        emit(UiState.Loading)
        if(email != "" && password != ""){
            if(email == "Yaska" && password == "Yaska123"){
                emit(UiState.Success("Berhasil login"))
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

    companion object{
        @Volatile
        private var instance: AuthRepository? = null

        fun getInstance(): AuthRepository =
            instance ?: synchronized(this){
                instance ?: AuthRepository()
            }.also { instance = it }
    }
}