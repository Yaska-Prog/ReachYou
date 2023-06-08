package com.example.reachyou.data.remote.retrofit

import com.example.reachyou.data.remote.response.LoginResponse
import com.example.reachyou.data.remote.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    suspend fun regisUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<RegisterResponse>

    @Multipart
    @POST("setupProfile")
    suspend fun setupProfile(
        @Part file: MultipartBody.Part,
        @Part("username") username: RequestBody
    ): Response<RegisterResponse>
}