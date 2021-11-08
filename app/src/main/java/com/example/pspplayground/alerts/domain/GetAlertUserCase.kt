package com.example.pspplayground.alerts.domain

class GetAlertUseCase(private val alertRepository: AlertRepository) {

    fun execute(): List<AlertModel> {
        return alertRepository.getAlerts()
    }
}

class GetEspecificAlertUseCase(private val alertRepository: AlertRepository) {

    fun execute(alertId: String): AlertModel? {
        return alertRepository.getAlert(alertId)
    }
}