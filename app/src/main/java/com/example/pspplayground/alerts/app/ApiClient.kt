package com.example.pspplayground.alerts.app

import com.example.pspplayground.alerts.data.AlertApiModel
import com.example.pspplayground.alerts.data.EspecificAlertApiModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

interface ApiClient {
    fun getAlerts(): List<AlertApiModel>
    fun getAlert(alertId: String): EspecificAlertApiModel?
}

class MockApiClient() : ApiClient {
    override fun getAlerts(): List<AlertApiModel> {
        return mutableListOf(
            AlertApiModel(
                "1",
                "Título del primer alertmodel",
                "Summary del primer alertmodel",
                "1",
                "04/11/2022"
            )
        )
    }

    override fun getAlert(alertId: String): EspecificAlertApiModel {
        return EspecificAlertApiModel(
            "1",
            "1",
            "Título del primer alertmodel",
            "1",
            "04/11/2022",
            "Summary del primer alertmodel",
            "Body del primer alertmodel",
            "Source del primer alertmodel",
            mutableListOf(),
        )
    }

}

class RetrofitApiClient() : ApiClient {

    private val urlEndPoint: String = "https://plagricola.sitehub.es/api/public/"
    private var apiEndPoint: ApiEndPoint

    init {
        apiEndPoint = buildApiService()
    }

    private fun buildApiService(): ApiEndPoint {
        return buildClient().create(ApiEndPoint::class.java)
    }

    private fun buildClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlEndPoint)
            .client(buildHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun buildHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

    override fun getAlerts(): List<AlertApiModel> {
        val call = apiEndPoint.getAlerts()
        val response = call.execute()
        return if (response.isSuccessful) {
            val alerts = response.body()!!.data
            alerts
        } else {
            mutableListOf()
        }
    }

    override fun getAlert(alertId: String): EspecificAlertApiModel? {
        val call = apiEndPoint.getAlert(alertId)
        val response = call.execute()
        return response.body()?.data
    }


}