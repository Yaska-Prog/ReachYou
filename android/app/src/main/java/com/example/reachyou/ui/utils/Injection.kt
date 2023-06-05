package com.example.reachyou.ui.utils

import android.content.Context
import com.example.reachyou.data.repository.AuthRepository

object Injection {
    fun provideAuthRepository(context: Context): AuthRepository{
        return AuthRepository.getInstance()
    }
}