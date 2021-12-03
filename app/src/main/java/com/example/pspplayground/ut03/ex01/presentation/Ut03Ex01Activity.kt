package com.example.pspplayground.ut03.ex01.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pspplayground.R

class Ut03Ex01Activity : AppCompatActivity() {

    private val TAG = Ut03Ex01Activity::class.java.simpleName
    private lateinit var thread1: Thread

    private lateinit var viewModel: Ut03Ex01ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ut03_ex01)
        viewModel = Ut03Ex01ViewModel()
        //exampleThread()
        exampleCoroutines()
    }

    private fun exampleCoroutines() {
        viewModel.getUserViewModelScope()
    }

    override fun onDestroy() {
        super.onDestroy()

        if (this::thread1.isInitialized){
            thread1.interrupt()
        }
    }



    private fun exampleThread() {
        thread1 = Thread(Runnable {
            var i = 0
            while (true) {
                Log.d(TAG, "Hola: $i")
                Thread.sleep(1000)
                i += 1
            }
        })
        thread1.start()
    }
}