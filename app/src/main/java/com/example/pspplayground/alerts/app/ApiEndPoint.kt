package com.example.pspplayground.alerts.app

import com.example.pspplayground.alerts.data.AlertApiModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndPoint {

    @GET("alerts")
    fun getAlerts(): Call<ResponseApiModel<List<AlertApiModel>>>
}