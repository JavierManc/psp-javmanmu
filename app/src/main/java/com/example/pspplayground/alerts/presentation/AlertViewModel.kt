package com.example.pspplayground.alerts.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pspplayground.alerts.domain.AlertModel
import com.example.pspplayground.alerts.domain.GetAlertUseCase
import com.example.pspplayground.alerts.domain.GetEspecificAlertUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlertViewModel(
    private val getAlertUseCase: GetAlertUseCase,
    private val getEspecificAlertUseCase: GetEspecificAlertUseCase
) : ViewModel() {

    val alertViewState: LiveData<List<AlertModel>>
        get() = _alertViewState


    private val _alertViewState: MutableLiveData<List<AlertModel>> by lazy {
        MutableLiveData<List<AlertModel>>()
    }

    fun getAlerts() {
        viewModelScope.launch(Dispatchers.IO) {
            _alertViewState.postValue(getAlertUseCase.execute())
        }
    }

    fun getAlert(id: String): AlertModel? {
        return getEspecificAlertUseCase.execute(id)
    }

    fun showAlert(alert: AlertModel) {
        viewModelScope.launch(Dispatchers.Main) {
            Log.d("Alert", alert.toString())
        }
    }
}