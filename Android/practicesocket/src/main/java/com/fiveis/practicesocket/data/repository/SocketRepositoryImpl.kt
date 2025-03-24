package com.fiveis.practicesocket.data.repository

import com.fiveis.practicesocket.data.remote.dto.SocketMessageDto
import com.fiveis.practicesocket.data.remote.socket.SocketManager
import com.fiveis.practicesocket.domain.repository.SocketRepository

/**
 * SocketRepositoryImpl은 도메인 계층의 SocketRepository 인터페이스를 구현하여,
 * SocketManager를 통해 웹소켓 관련 기능을 제공합니다.
 */
class SocketRepositoryImpl(private val socketManager: SocketManager) : SocketRepository {
    override fun connectSocket() {
        socketManager.connect()
    }

    override fun disconnectSocket() {
        socketManager.disconnect()
    }

    override fun sendSocketMessage(message: SocketMessageDto) {
        socketManager.sendMessage(message)
    }

    override fun setSocketListener(listener: SocketManager.SocketListener) {
        socketManager.setSocketListener(listener)
    }
}
