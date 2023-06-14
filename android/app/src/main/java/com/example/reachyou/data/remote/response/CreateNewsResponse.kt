package com.example.reachyou.data.remote.response

import com.google.gson.annotations.SerializedName

data class CreateNewsResponse(

	@field:SerializedName("msg")
	val msg: String,

	@field:SerializedName("article")
	val article: Article
)

data class Article(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("profil")
	val profil: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("userUUID")
	val userUUID: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
