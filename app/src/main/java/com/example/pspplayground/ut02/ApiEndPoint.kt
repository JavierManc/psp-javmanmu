package com.example.pspplayground.ut02

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * EndPoints de los servicios que se van a usar
 * Es un requisito de Retrofit el crear esta interfaz
 */
interface ApiEndPoint {

    @GET("users")
    fun getUsers(): Call<List<UserApiModel>>

    @GET("posts")
    fun getPosts(): Call<List<PostApiModel>>

    @GET("users/{id}")
    fun getUser(@Path("id") userId: Int): Call<UserApiModel>


}