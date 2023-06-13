package com.example.reachyou.model

import android.os.Parcelable


data class UserModel(
    var username: String,
    var email: String,
    var id: String,
    var koin: Int,
    var profileUrl: String
)