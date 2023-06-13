package com.example.reachyou.data.local

import android.content.Context
import com.example.reachyou.model.UserModel

class SharedPreferenceManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("userPref", Context.MODE_PRIVATE)

    fun saveUser(user: UserModel){
        sharedPreferences.edit()
            .putString("username", user.username)
            .putString("email", user.email)
            .putString("id", user.id)
            .putInt("koin", user.koin)
            .apply()
    }

    fun getUser(): UserModel? {
        val username = sharedPreferences.getString("username", null)
        val email = sharedPreferences.getString("email", null)
        val id = sharedPreferences.getString("id", null)
        val coin = sharedPreferences.getInt("koin", 0)
        return if(username != null && email != null){
            UserModel(username, email, id as String, coin)
        } else{
            null
        }
    }

    fun logout(){
        sharedPreferences.edit().clear().apply()
    }
}