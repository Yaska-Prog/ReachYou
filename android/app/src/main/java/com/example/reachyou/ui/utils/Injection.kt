package com.example.reachyou.ui.utils

import android.content.Context
import com.example.reachyou.data.local.SharedPreferenceManager
import com.example.reachyou.data.remote.retrofit.ApiConfig
import com.example.reachyou.data.repository.AuthRepository

object Injection {
    fun provideAuthRepository(context: Context): AuthRepository{
        val sharedPref = SharedPreferenceManager(context)
        val apiService = ApiConfig(sharedPref).getApiService()
        return AuthRepository.getInstance(apiService)
    }
}