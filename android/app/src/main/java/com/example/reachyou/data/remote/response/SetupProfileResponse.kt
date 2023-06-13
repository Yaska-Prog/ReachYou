package com.example.reachyou.data.remote.response

import com.google.gson.annotations.SerializedName

data class SetupProfileResponse(

	@field:SerializedName("msg")
	val msg: String,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("username")
	val username: String
)
