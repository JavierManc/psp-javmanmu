package com.example.pspplayground.ut02.exercise1

class UserRepository(private val apiClient: ApiClient) {

    fun getUser(id: Int): UserApiModel? {
        return apiClient.getUser(id)
    }

    fun getUsers(): List<UserApiModel> {
        return apiClient.getUsers()
    }
}