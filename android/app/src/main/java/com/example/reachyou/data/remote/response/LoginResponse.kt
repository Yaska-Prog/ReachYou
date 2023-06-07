package com.example.reachyou.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)