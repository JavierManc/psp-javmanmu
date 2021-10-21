package com.example.pspplayground.ut02

/**
 * Modelo de datos de usuarios recibido desde la API.
 */
data class UserApiModel(val id: Int, val name: String, val username: String, val email: String)

data class PostApiModel(val userId: Int, val id: Int, val title: String, val body: String)