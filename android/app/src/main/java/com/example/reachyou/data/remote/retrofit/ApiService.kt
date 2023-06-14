package com.example.reachyou.data.remote.retrofit

import com.example.reachyou.data.remote.response.ArticleMainResponse
import com.example.reachyou.data.remote.response.CreateNewsResponse
import com.example.reachyou.data.remote.response.LoginResponse
import com.example.reachyou.data.remote.response.RegisterMainResponse
import com.example.reachyou.data.remote.response.RegisterResponse
import com.example.reachyou.data.remote.response.SetupProfileResponse
import com.example.reachyou.data.remote.response.UpdateProfilePictureResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

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
    ): Response<RegisterMainResponse>

    @FormUrlEncoded
    @PATCH("/tambahpoint/{id}")
    suspend fun updateCoin(
        @Path("id") id: String,
        @Field("newPoint") coin: Int
    ): Response<RegisterResponse>

    @GET("article")
    suspend fun getAllArticle(): Response<List<ArticleMainResponse>>

    @GET("/article/{id}")
    suspend fun getDetailArticle(
        @Path("id") id: Int
    ): Response<ArticleMainResponse>

    @FormUrlEncoded
    @PATCH("/updateusername/{id}")
    suspend fun updateUsername(
        @Path("id") uuid: String,
        @Field("username") username: String
    ): Response<RegisterResponse>

    @FormUrlEncoded
    @PATCH("/updateemail/{id}")
    suspend fun updateEmail(
        @Path("id") uuid: String,
        @Field("email") email: String
    ): Response<RegisterResponse>

    @Multipart
    @POST("/bug")
    suspend fun laporBug(
        @Part file: MultipartBody.Part,
        @Part("email") email: RequestBody,
        @Part("bug") bug: RequestBody
    ): Response<RegisterResponse>

    @Multipart
    @PATCH("/setup/{id}")
    suspend fun setupProfile(
        @Path("id") id: String,
        @Part file: MultipartBody.Part,
        @Part("username") username: RequestBody
    ): Response<SetupProfileResponse>

    @Multipart
    @PATCH("/profil/{id}")
    suspend fun updateProfilePicture(
        @Path("id") id: String,
        @Part file: MultipartBody.Part,
    ): Response<UpdateProfilePictureResponse>

    @Multipart
    @POST("/articlegambar/{id}")
    suspend fun createNews(
        @Path("id") id: String,
        @Part file: MultipartBody.Part,
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody
    ): Response<CreateNewsResponse>
}