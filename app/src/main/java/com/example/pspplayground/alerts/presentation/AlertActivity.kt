package com.example.pspplayground.alerts.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.pspplayground.alerts.app.RetrofitApiClient
import com.example.pspplayground.alerts.data.AlertDataRepository
import com.example.pspplayground.alerts.data.AlertRemoteSource
import com.example.pspplayground.alerts.domain.AlertModel
import com.example.pspplayground.alerts.domain.GetAlertUseCase
import com.example.pspplayground.alerts.domain.GetEspecificAlertUseCase
import com.example.pspplayground.databinding.ActivityAlertBinding


class AlertActivity : AppCompatActivity() {

    private val TAG = AlertActivity::class.java.simpleName

    private val alertModel: AlertViewModel = AlertViewModel(
        GetAlertUseCase(
            AlertDataRepository(
                AlertRemoteSource(RetrofitApiClient())
            )
        ),
        GetEspecificAlertUseCase(
            AlertDataRepository(
                AlertRemoteSource(RetrofitApiClient())
            )
        )
    )

    private lateinit var viewBanding: ActivityAlertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBinding()
        setupViewStateObserver()
        alertModel.getAlerts()
    }

    private fun setUpBinding() {
        viewBanding = ActivityAlertBinding.inflate(layoutInflater)
        setContentView(viewBanding.root)
    }

    private fun setupViewStateObserver() {
        val nameObserver = Observer<List<AlertModel>> {
            alertModel.alertViewState.value?.forEach { model ->
                render(model)
                alertModel.getAlert(model.id)?.let { it1 -> alertModel.showAlert(it1) }
            }
        }
        alertModel.alertViewState.observe(this, nameObserver)
    }

    private fun render(alert: AlertModel) {
        viewBanding.infoTitleText.text = alert.title
        viewBanding.infoDateText.text = alert.datePublished
        viewBanding.infoBodyText.text = alert.body
    }
}