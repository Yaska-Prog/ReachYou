package com.example.reachyou.ui.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.reachyou.data.repository.AuthRepository
import com.example.reachyou.ui.screen.login.LoginViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val authRepository: AuthRepository? = null): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(authRepository as AuthRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getUserInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(authRepository = Injection.provideAuthRepository(context = context))
            }.also { instance = it }
    }
}