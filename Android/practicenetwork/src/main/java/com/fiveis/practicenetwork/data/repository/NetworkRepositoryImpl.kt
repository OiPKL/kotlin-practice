package com.fiveis.practicenetwork.data.repository

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.net.wifi.WifiNetworkSuggestion
import android.widget.Toast
import android.provider.Settings
import com.fiveis.practicenetwork.domain.repository.NetworkRepository

class NetworkRepositoryImpl(private val context: Context) : NetworkRepository {

    private val wifiManager =
        context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    override suspend fun connectToNetwork(ssid: String, password: String) {
        try {
            // Wi-Fi가 꺼져 있으면, 사용자에게 Wi-Fi 설정 화면을 열도록 안내
            if (!wifiManager.isWifiEnabled) {
                Toast.makeText(context, "Wi-Fi가 꺼져 있습니다. Wi-Fi 설정으로 이동합니다.", Toast.LENGTH_SHORT).show()
                context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
                return
            }

            // WifiNetworkSuggestion을 사용하여 지속적인 연결 제안 등록
            val suggestion = WifiNetworkSuggestion.Builder()
                .setSsid(ssid)
                .setWpa2Passphrase(password)
                .build()

            val suggestionsList = listOf(suggestion)
            // 네트워크 제안 등록
            val status = wifiManager.addNetworkSuggestions(suggestionsList)

            if (status == WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS) {
                Toast.makeText(context, "네트워크 제안 등록 성공. OS가 자동 연결 시도합니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "네트워크 제안 등록 실패: $status", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "연결 예외: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}