package com.example.pspplayground.ut02

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *Abstracción del cliente que vamos a usar en la actividad
 */
interface ApiClient {
    fun getUsers(): List<UserApiModel>
    fun getPosts(): List<PostApiModel>
    fun getUser(userId: Int): UserApiModel?
    //fun getUsers(callback: ApiCallback<List<UserApiModel>>)

}

/**
 * Función que crea un listado de clientes que se añaden a mano
 */
class MockApiClient : ApiClient {
    override fun getUsers(): List<UserApiModel> {
        return mutableListOf(
            UserApiModel(1, "User1", "Usurname1", "user1@gmail.com"),
            UserApiModel(2, "User2", "Usurname2", "user2@gmail.com"),
            UserApiModel(3, "User3", "Usurname3", "user3@gmail.com"),
            UserApiModel(4, "User4", "Usurname4", "user4@gmail.com"),
            UserApiModel(5, "User5", "Usurname5", "user5@gmail.com"),
        )
    }

    override fun getPosts(): List<PostApiModel> {
        return mutableListOf(
            PostApiModel(1, 1, "Title1", "Body1"),
            PostApiModel(2, 2, "Title2", "Body2"),
            PostApiModel(3, 3, "Title3", "Body3"),
            PostApiModel(4, 4, "Title4", "Body4"),
            PostApiModel(5, 5, "Title5", "Body5"),

            )
    }

    override fun getUser(userId: Int): UserApiModel {
        return UserApiModel(1, "User1", "Usurname1", "user1@gmail.com")
    }


}


class RetrofitApiClient : ApiClient {

    private val urlEndPoint: String = "https://jsonplaceholder.typicode.com/"
    private var apiEndPoint: ApiEndPoint

    init {
        apiEndPoint = buildApiService()
    }

    /**
     * Creación del cliente con el Endpoint
     * Definido por Retrofit
     */
    private fun buildApiService(): ApiEndPoint {
        return buildClient().create(ApiEndPoint::class.java)
    }

    /**
     * Creación y configuración del cliente Retrofit.
     * Siempre es asi
     */
    private fun buildClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlEndPoint)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Endpoint para obtener un listado de usuarios
     */
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

    /**
     * Endpoint para obtener un listado de posts
     */
    override fun getPosts(): List<PostApiModel> {
        val call = apiEndPoint.getPosts()
        val response = call.execute()
        return if (response.isSuccessful) {
            val posts = response.body()
            posts ?: mutableListOf()
        } else {
            mutableListOf()
        }
    }

    /**
     * Endpoint para obtener un usuario en concreto
     */
    override fun getUser(userId: Int): UserApiModel? {
        val call = apiEndPoint.getUser(userId)
        val response = call.execute()
        return response.body()
    }
}

