package com.example.pspplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var label: TextView
    lateinit var spinner: ProgressBar
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
    }

    private fun setUpView() {
        label = findViewById(R.id.label)
        spinner = findViewById(R.id.spinner)
        button = findViewById(R.id.button)
        button.setOnClickListener {
            //launchARM()
            //withThread()
            //withThreadAndPost()
            //threadFromParam()
            //launchMultipleThreads()
            //launchInsideThread()
            //postDelayed()
            threadProgressBar()
        }
    }

    private fun launchARM() {
        for (i in 1..100) {
            label.text = "Hola $i"
            Thread.sleep(2000)
        }
    }

    private fun withThread() {
        Thread(Runnable {
            for (i in 1..100) {
                label.text = "Hola $i"
                Thread.sleep(2000)
            }
        }).start()
    }

    private fun withThreadAndPost() {
        Thread(Runnable {
            for (i in 1..100) {
                label.post {
                    label.text = "Hola $i"
                }

                Thread.sleep(2000)
            }
        }).start()
    }

    private fun withRunUiThread() {
        Thread(Runnable {
            for (i in 1..100) {
                runOnUiThread {
                    label.text = "Hola $i"
                }

                Thread.sleep(2000)
            }
        }).start()
    }

    private fun threadFromParam() {
        val thread = Thread(Runnable {
            for (i in 1..100) {
                runOnUiThread {
                    label.text = "Hola $i"
                }

                Thread.sleep(2000)
            }
        })

        thread.start();
    }

    private fun launchMultipleThreads() {
        val thread1 = Thread(Runnable {
            for (i in 1..100) {
                runOnUiThread {
                    Log.d("@dev", "Thread-1 $i")
                    Thread.sleep(1000)
                }


            }
        })

        val thread2 = Thread(Runnable {
            for (i in 1..100) {
                runOnUiThread {
                    Log.d("@dev", "Thread-2 $i")
                    Thread.sleep(1500)
                }


            }
        })

        val thread3 = Thread(Runnable {
            for (i in 1..100) {
                runOnUiThread {
                    Log.d("@dev", "Thread-3 $i")
                    Thread.sleep(2000)
                }


            }
        })

        thread3.start()
        thread1.start()
        thread2.start()
    }

    private fun launchInsideThread() {
        Thread(Runnable {
            Thread(Runnable {
                for (i in 1..100) {
                    runOnUiThread {
                        Log.d("@dev", "Thread-2 $i")
                        Thread.sleep(1000)
                    }
                }
            }).start()

            for (i in 1..100) {
                runOnUiThread {
                    Log.d("@dev", "Thread-1 $i")
                    Thread.sleep(1000)
                }
            }
        }).start()
    }

    /**
     * Hilo con delay
     */
    private fun postDelayed() {
        Handler(Looper.getMainLooper()).postDelayed({
            label.text = "Hola!"
        }, 3000)
    }

    private fun threadProgressBar() {
        Thread(Runnable {
            for (i in 1..10) {
                runOnUiThread {
                    label.text = "Hola $i"
                }
                Thread.sleep(1000)
            }

            runOnUiThread {
                spinner.visibility = View.VISIBLE
            }

            Thread(Runnable {
                Handler(Looper.getMainLooper()).postDelayed({
                    spinner.visibility = View.GONE
                }, 3000)
            }).start()
        }).start()

    }
}