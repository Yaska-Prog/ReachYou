package com.example.reachyou.data.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterMainResponse(
    @field:SerializedName("msg")
    val msg: String,

    @field:SerializedName("uuid")
    val uuid: String,
)
