package com.example.pspplayground.alerts.presentation

import androidx.lifecycle.ViewModel
import com.example.pspplayground.alerts.domain.AlertModel
import com.example.pspplayground.alerts.domain.GetAlertUseCase
import com.example.pspplayground.alerts.domain.GetEspecificAlertUseCase

class AlertViewModel(
    private val getAlertUseCase: GetAlertUseCase,
    private val getEspecificAlertUseCase: GetEspecificAlertUseCase
) : ViewModel() {

    fun getAlerts(): List<AlertModel> = getAlertUseCase.execute()
    fun getAlert(alertId: String): AlertModel? = getEspecificAlertUseCase.execute(alertId)
}