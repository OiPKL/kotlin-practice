package com.fiveis.practicenetwork.domain.usecase

import com.fiveis.practicenetwork.domain.repository.NetworkRepository

class ConnectNetworkUseCase(private val repository: NetworkRepository) {
    suspend operator fun invoke(ssid: String, password: String) {
        repository.connectToNetwork(ssid, password)
    }
}