package com.example.pspplayground.alerts.data

import com.example.pspplayground.alerts.app.ApiClient
import com.example.pspplayground.alerts.domain.AlertModel

class AlertRemoteSource(private var apiClient: ApiClient) {

    fun getAlerts(): List<AlertModel> {
        val alerts = apiClient.getAlerts()
        return alerts.map { alertApiModel -> alertApiModel.toModel() }
    }

    fun getAlert(alertId: String): AlertModel?{
        return apiClient.getAlert(alertId)?.toModel()
    }

}