package com.example.reachyou.data.local

import android.content.Context
import com.example.reachyou.model.UserModel

class SharedPreferenceManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("userPref", Context.MODE_PRIVATE)

    fun saveUser(user: UserModel){
        sharedPreferences.edit()
            .putString("username", user.usernname)
            .putString("email", user.email)
            .putString("id", user.id)
            .apply()
    }

    fun getUser(): UserModel? {
        val username = sharedPreferences.getString("username", null)
        val email = sharedPreferences.getString("email", null)
        val id = sharedPreferences.getString("id", null)
        return if(username != null && email != null){
            UserModel(username, email, id as String)
        } else{
            null
        }
    }

    fun clear(){
        sharedPreferences.edit().clear().apply()
    }
}