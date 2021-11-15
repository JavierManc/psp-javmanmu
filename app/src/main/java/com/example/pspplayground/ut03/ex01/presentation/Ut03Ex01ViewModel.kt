package com.example.pspplayground.ut03.ex01.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pspplayground.ut03.ex01.app.RetrofitApiClient
import kotlinx.coroutines.*

class Ut03Ex01ViewModel() : ViewModel() {

    fun getUsersGlobalScope() {
        viewModelScope.launch(Dispatchers.Main) {
            var i = 0
            while (true) {
                Log.d("@dev", "Hola: $i")
                Thread.sleep(1000)
                i += 1
            }
        }
        Log.d("@dev", "Ui Thread")
    }

    fun getUserViewModelScope() {
        val apiClient = RetrofitApiClient()
        viewModelScope.launch(Dispatchers.Main) {
            Log.d("@dev", "Llamo a API from ViewModelScope....")
            val users = apiClient.getUsers()
            Log.d("@dev", "$users")
        }

        GlobalScope.launch(Dispatchers.IO) {
            Log.d("@dev", "Llamo a API.....")
            val users = apiClient.getUsers()
            Log.d("@dev", "$users")
        }
    }


}