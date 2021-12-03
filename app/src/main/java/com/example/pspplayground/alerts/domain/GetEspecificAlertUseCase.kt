package com.example.pspplayground.alerts.domain

class GetEspecificAlertUseCase(private val alertRepository: AlertRepository) {

    fun execute(alertId: String): AlertModel? {
        return alertRepository.getAlert(alertId)
    }
}