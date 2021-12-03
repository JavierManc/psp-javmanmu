package com.example.pspplayground.ut02.exercise1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.pspplayground.R

class Exercise01Activity : AppCompatActivity() {

    private val TAG = Exercise01Activity::class.java.simpleName

    private val mockApiClient: ApiClient = MockApiClient()
    private val retrofitApiClient: ApiClient = RetrofitApiClient()

    lateinit var actionMock: Button
    lateinit var actionApi: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise01)

        actionMock = findViewById(R.id.action_mock)
        actionApi = findViewById(R.id.action_api)

        actionMock.setOnClickListener {
            val userRepository = UserRepository(mockApiClient)
            showUser(userRepository.getUser(1))
            showUsers(userRepository.getUsers())
        }

        actionApi.setOnClickListener {
            val userRepository = UserRepository(retrofitApiClient)
            showUser(userRepository.getUser(1))
            showUsers(userRepository.getUsers())
        }
    }

    private fun showUser(user: UserApiModel?) {
        runOnUiThread {
            Log.d(TAG, "getUser: $user")
        }
    }

    private fun showUsers(userList: List<UserApiModel>) {
        runOnUiThread {
            userList.forEach {
                Log.d(TAG, "getUsers: $it")
            }
        }
    }


}