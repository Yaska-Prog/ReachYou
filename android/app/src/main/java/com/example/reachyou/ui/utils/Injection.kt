package com.example.reachyou.ui.utils

import android.content.Context
import com.example.reachyou.data.local.SharedPreferenceManager
import com.example.reachyou.data.local.database.QuestionRoomDatabase
import com.example.reachyou.data.remote.retrofit.ApiConfig
import com.example.reachyou.data.repository.AuthRepository
import com.example.reachyou.data.repository.NewsRepository
import com.example.reachyou.data.repository.QuizRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

object Injection {
    fun provideAuthRepository(context: Context): AuthRepository{
        val sharedPref = SharedPreferenceManager(context)
        val apiService = ApiConfig().getApiService()
        return AuthRepository.getInstance(apiService, sharedPref)
    }

    fun provideQuizRepository(context: Context): QuizRepository{
        val apiService = ApiConfig().getApiService()
        val applicationScope = CoroutineScope(SupervisorJob())
        val database = QuestionRoomDatabase.getDatabase(context = context, applicationScope = applicationScope)
        val questionDao = database.questionDao()
        return QuizRepository.getInstance(apiService, questionDao)
    }

    fun provideNewsRepository(context: Context): NewsRepository{
        val sharedPref = SharedPreferenceManager(context)
        val apiService = ApiConfig().getApiService()
        return NewsRepository.getInstance(apiService, sharedPref)
    }
}