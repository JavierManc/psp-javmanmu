package com.example.pspplayground.alerts.domain

interface AlertRepository {

    fun getAlerts():List<AlertModel>

}