package com.example.pspplayground.ut02.exercise1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.pspplayground.R

class Exercise01Activity : AppCompatActivity() {

    private val TAG = Exercise01Activity::class.java.simpleName

    private val mockApiClient : ApiClient = MockApiClient()
    private val retrofitApiClient : ApiClient = RetrofitApiClient()

    lateinit var actionMock: Button
    lateinit var actionApi: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise01)

        actionMock= findViewById(R.id.action_mock)
        actionApi= findViewById(R.id.action_api)

        actionMock.setOnClickListener {
            val userRepository = UserRepository(mockApiClient)
            val user : UserApiModel? = userRepository.getUser(1)
            Log.d(TAG, "getUser: $user")
            val users : List<UserApiModel> = userRepository.getUsers()
            users.forEach {
                Log.d(TAG, "getUsers: $it")
            }
        }
    }


}