package com.fiveis.practicesocket.domain.repository

import com.fiveis.practicesocket.data.remote.dto.SocketMessageDto
import com.fiveis.practicesocket.data.remote.socket.SocketManager

/**
 * SocketRepository 인터페이스는 웹소켓 통신 기능을 추상화합니다.
 */
interface SocketRepository {
    fun connectSocket()
    fun disconnectSocket()
    fun sendSocketMessage(message: SocketMessageDto)
    fun setSocketListener(listener: SocketManager.SocketListener)
}
