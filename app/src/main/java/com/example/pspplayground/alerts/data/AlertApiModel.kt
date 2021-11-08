package com.example.pspplayground.alerts.data

import com.example.pspplayground.alerts.domain.AlertModel

data class AlertApiModel(
    val alert_id: String,
    val title: String,
    val summary: String,
    val type: String,
    val date: String
){
    fun toModel(): AlertModel = AlertModel(alert_id, title, type.toInt(),summary,date,summary,"", mutableListOf())
}

