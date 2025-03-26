package com.fiveis.practicenetwork.domain.repository

interface NetworkRepository {
    suspend fun connectToNetwork(ssid: String, password: String)
}