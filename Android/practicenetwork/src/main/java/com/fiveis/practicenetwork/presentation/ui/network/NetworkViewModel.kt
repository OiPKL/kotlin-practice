package com.fiveis.practicenetwork.presentation.ui.network

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.fiveis.practicenetwork.data.config.NetworkConfig
import com.fiveis.practicenetwork.data.repository.NetworkRepositoryImpl
import com.fiveis.practicenetwork.domain.usecase.ConnectNetworkUseCase
import kotlinx.coroutines.launch

class NetworkViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NetworkRepositoryImpl(application.applicationContext)
    private val connectNetworkUseCase = ConnectNetworkUseCase(repository)

    fun connectToConfiguredNetwork() {
        viewModelScope.launch {
            connectNetworkUseCase(NetworkConfig.SSID, NetworkConfig.PASSWORD)
        }
    }
}