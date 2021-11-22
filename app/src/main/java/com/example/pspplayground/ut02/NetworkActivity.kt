package com.example.pspplayground.ut02

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pspplayground.R

class NetworkActivity : AppCompatActivity() {

    /**
     * Creación del cliente
     */
    private val mockApiClient: ApiClient= MockApiClient()
    private val apiClient: ApiClient = RetrofitApiClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)
        testAsyncApiUser(1)
    }


    private fun testSyncApi() {
        val users = apiClient.getUsers()
        if (users.isNotEmpty()) {
            users.forEach {
                Log.i(TAG, "$it")
            }
        } else {
            Log.i(TAG, "User list is empty")
        }
    }

    private fun testAsyncApi() {
        val threadNetwork = Thread(Runnable {
            val users = apiClient.getUsers()
            if (users.isNotEmpty()){
                users.forEach {
                    Log.i(TAG, "$it")
                }
            }else{
                Log.i(TAG, "User list is empty")
            }
        })
        threadNetwork.start()
    }

    private fun testAsyncApiPosts() {
        Thread(Runnable {
            val posts = apiClient.getPosts()
            if (posts.isNotEmpty()){
                posts.forEach {
                    Log.i(TAG, "$it")
                }
            }else{
                Log.i(TAG, "Posts list is empty")
            }
        }).start()
    }

    private fun testAsyncApiUser(userId: Int){
        Thread(Runnable {
            val user = apiClient.getUser(userId)
            if (user != null){
                Log.i(TAG, "$user")
            }else{
                Log.i(TAG, "User not founded")
            }
        }).start()
    }


}