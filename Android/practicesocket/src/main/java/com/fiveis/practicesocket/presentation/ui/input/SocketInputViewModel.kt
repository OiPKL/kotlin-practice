package com.fiveis.practicesocket.presentation.ui.input

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.fiveis.practicesocket.data.remote.dto.SocketMessageDto
import com.fiveis.practicesocket.data.remote.socket.SocketManager
import com.fiveis.practicesocket.domain.usecase.ConnectSocketUseCase
import com.fiveis.practicesocket.domain.usecase.DisconnectSocketUseCase
import com.fiveis.practicesocket.domain.usecase.SendSocketMessageUseCase
import com.fiveis.practicesocket.domain.repository.SocketRepository
import com.fiveis.practicesocket.data.config.DefaultConnectSocketUseCase
import com.fiveis.practicesocket.data.config.DefaultDisconnectSocketUseCase
import com.fiveis.practicesocket.data.config.DefaultSendSocketMessageUseCase
import com.fiveis.practicesocket.data.config.DefaultSocketRepository

class SocketInputViewModel(
    private val connectSocketUseCase: ConnectSocketUseCase = DefaultConnectSocketUseCase,
    private val disconnectSocketUseCase: DisconnectSocketUseCase = DefaultDisconnectSocketUseCase,
    private val sendSocketMessageUseCase: SendSocketMessageUseCase = DefaultSendSocketMessageUseCase,
    private val repository: SocketRepository = DefaultSocketRepository
) : ViewModel(), SocketManager.SocketListener {

    private val _connectionStatus = MutableStateFlow("소켓연결전")
    val connectionStatus: StateFlow<String> = _connectionStatus.asStateFlow()

    private val _receivedMessage = MutableStateFlow("")
    val receivedMessage: StateFlow<String> = _receivedMessage.asStateFlow()

    init {
        repository.setSocketListener(this)
    }

    fun connectSocket() {
        _connectionStatus.value = "연결 시도중"
        connectSocketUseCase.execute()
    }

    fun disconnectSocket() {
        disconnectSocketUseCase.execute()
        _connectionStatus.value = "소켓연결전"
    }

    fun sendMessage(jwt: String, cmd: String, sender: String) {
        val message = SocketMessageDto(sender = sender, jwt = jwt, cmd = cmd)
        sendSocketMessageUseCase.execute(message)
    }

    // SocketManager.SocketListener 구현
    override fun onConnected() {
        _connectionStatus.value = "소켓연결성공"
    }

    override fun onDisconnected() {
        _connectionStatus.value = "소켓연결전"
    }

    override fun onMessageReceived(message: SocketMessageDto) {
        val display = "jwt: ${message.jwt}\ncmd: ${message.cmd}\nsender: ${message.sender}"
        _receivedMessage.value = display
    }

    override fun onError(error: String) {
        _connectionStatus.value = "오류: $error"
    }

    override fun onReconnecting() {
        _connectionStatus.value = "재연결시도중"
    }
}