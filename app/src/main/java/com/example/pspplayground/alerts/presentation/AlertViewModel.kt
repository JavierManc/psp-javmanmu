package com.example.pspplayground.alerts.presentation

import androidx.lifecycle.ViewModel
import com.example.pspplayground.alerts.domain.AlertModel
import com.example.pspplayground.alerts.domain.GetAlertUseCase

class AlertViewModel(private val getAlertUseCase: GetAlertUseCase): ViewModel() {

    fun getAlerts(): List<AlertModel> = getAlertUseCase.execute()
}