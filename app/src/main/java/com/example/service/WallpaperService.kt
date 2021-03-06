package com.example.service

import com.example.service.model.WalModel
import com.example.service.model.Example
import retrofit2.Call
import retrofit2.http.*

interface WallpaperService {
    @GET("/photos")
    fun getMealPlanse(
        @Query("client_id") host: String,
        @Query("redirect_uri") key: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int = 30
    ): Call<List<WalModel>>

    @GET("search/photos")
    fun serch(
        @Query("client_id") host: String,
        @Query("redirect_uri") key: String,
        @Query("page") page: Int,
        @Query("query") query: String,
        @Query("per_page") per_page: Int = 30
    ): Call<Example>
}