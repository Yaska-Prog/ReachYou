package com.example.reachyou.data.remote.response

import com.google.gson.annotations.SerializedName

data class UpdateProfilePictureResponse(

	@field:SerializedName("msg")
	val msg: String,

	@field:SerializedName("url")
	val url: String
)
