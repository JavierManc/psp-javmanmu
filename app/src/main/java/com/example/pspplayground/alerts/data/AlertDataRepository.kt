package com.example.pspplayground.alerts.data

import com.example.pspplayground.alerts.domain.AlertModel
import com.example.pspplayground.alerts.domain.AlertRepository

class AlertDataRepository(private val alertRemoteSource: AlertRemoteSource) : AlertRepository {

    override fun getAlerts(): List<AlertModel> {
        val models = alertRemoteSource.getAlerts()
        return models
    }


}