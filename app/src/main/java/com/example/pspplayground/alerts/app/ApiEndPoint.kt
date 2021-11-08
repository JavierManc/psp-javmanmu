package com.example.pspplayground.alerts.app

import com.example.pspplayground.alerts.data.AlertApiModel
import com.example.pspplayground.alerts.data.EspecificAlertApiModel
import com.example.pspplayground.alerts.data.FileApiModel

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndPoint {

    @GET("alerts")
    fun getAlerts(): Call<ResponseApiModel<List<AlertApiModel>>>

    @GET("alerts/{id}")
    fun getAlert(@Path("id") alertId: String): Call<ResponseApiModel<EspecificAlertApiModel>>
}