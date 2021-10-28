package com.example.pspplayground.ut02.exercise1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ApiClient {
    fun getUser(id: Int): UserApiModel?
    fun getUsers(): List<UserApiModel>
}

class MockApiClient() : ApiClient {
    override fun getUser(id: Int): UserApiModel {
        return UserApiModel(id, "Name1", "Username1", "usermail1@gmail.com")
    }

    override fun getUsers(): List<UserApiModel> {
        return mutableListOf(
            UserApiModel(1, "Name1", "Username1", "usermail1@gmail.com"),
            UserApiModel(2, "Name2", "Username2", "usermail2@gmail.com"),
            UserApiModel(3, "Name3", "Username3", "usermail3@gmail.com"),
            UserApiModel(4, "Name4", "Username4", "usermail4@gmail.com")
        )
    }

}

class RetrofitApiClient() : ApiClient {

    private val urlEndPoint: String = "https://jsonplaceholder.typicode.com/"
    private var apiEndPoint: ApiEndPoint

    init {
        apiEndPoint = buildApiService()
    }

    override fun getUser(id: Int): UserApiModel? {
        val call = apiEndPoint.getUser(id)
        val response = call.execute()
        return response.body()
    }

    private fun buildApiService(): ApiEndPoint {
        return buildClient().create(ApiEndPoint::class.java)
    }

    private fun buildClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlEndPoint)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun getUsers(): List<UserApiModel> {
        val call = apiEndPoint.getUsers()
        val response = call.execute()
        return if (response.isSuccessful) {
            val users = response.body()
            users ?: mutableListOf()
        } else {
            mutableListOf()
        }
    }

}