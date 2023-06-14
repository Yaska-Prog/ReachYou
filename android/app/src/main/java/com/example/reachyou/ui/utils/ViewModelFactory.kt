package com.example.reachyou.ui.utils

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.reachyou.data.repository.AuthRepository
import com.example.reachyou.data.repository.NewsRepository
import com.example.reachyou.data.repository.QuizRepository
import com.example.reachyou.ui.component.utils.BottomSheetViewModel
import com.example.reachyou.ui.screen.createNews.CreateNewsViewmodel
import com.example.reachyou.ui.screen.detailNews.DetailNewsViewModel
import com.example.reachyou.ui.screen.detailQuiz.DetailQuizViewmodel
import com.example.reachyou.ui.screen.laporBug.LaporBugViewModel
import com.example.reachyou.ui.screen.login.LoginViewModel
import com.example.reachyou.ui.screen.news.NewsViewModel
import com.example.reachyou.ui.screen.profile.ProfileViewModel
import com.example.reachyou.ui.screen.register.RegisterViewmodel
import com.example.reachyou.ui.screen.setupProfile.SetUpProfileViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val authRepository: AuthRepository? = null, private val quizRepository: QuizRepository? = null, private val newsRepository: NewsRepository? = null): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(authRepository as AuthRepository) as T
        }
        else if(modelClass.isAssignableFrom(RegisterViewmodel::class.java)){
            return RegisterViewmodel(authRepository as AuthRepository) as T
        }
        else if(modelClass.isAssignableFrom(SetUpProfileViewModel::class.java)){
            return SetUpProfileViewModel(authRepository as AuthRepository) as T
        }
        else if(modelClass.isAssignableFrom(DetailQuizViewmodel::class.java)){
            return DetailQuizViewmodel(quizRepository as QuizRepository) as T
        }
        else if(modelClass.isAssignableFrom(NewsViewModel::class.java)){
            return NewsViewModel(newsRepository as NewsRepository) as T
        }
        else if(modelClass.isAssignableFrom(DetailNewsViewModel::class.java)){
            return DetailNewsViewModel(newsRepository as NewsRepository) as T
        }
        else if(modelClass.isAssignableFrom(BottomSheetViewModel::class.java)){
            return BottomSheetViewModel(authRepository as AuthRepository) as T
        }
        else if(modelClass.isAssignableFrom(LaporBugViewModel::class.java)){
            return LaporBugViewModel(authRepository as AuthRepository) as T
        }
        else if(modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(authRepository as AuthRepository) as T
        }
        else if(modelClass.isAssignableFrom(CreateNewsViewmodel::class.java)){
            return CreateNewsViewmodel(newsRepository as NewsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getUserInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(authRepository = Injection.provideAuthRepository(context))
            }.also { instance = it }

        private var quizInstance: ViewModelFactory? = null
        fun getQuizInstance(context: Context): ViewModelFactory =
            quizInstance ?: synchronized(this){
                quizInstance ?: ViewModelFactory(quizRepository = Injection.provideQuizRepository(context))
            }.also { quizInstance = it }

        private var newsInstance: ViewModelFactory? = null
        fun getNewsInstance(context: Context): ViewModelFactory =
            newsInstance ?: synchronized(this){
                newsInstance ?: synchronized(this){
                    newsInstance ?: ViewModelFactory(newsRepository = Injection.provideNewsRepository(context = context))
                }.also { newsInstance = it }
            }
    }
}