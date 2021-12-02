package com.example.pspplayground.ut02.exercise1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndPoint {

    @GET("users/{id}")
    fun getUser(@Path("id") userId: Int): Call<UserApiModel>

    @GET("users")
    fun getUsers(): Call<List<UserApiModel>>
}