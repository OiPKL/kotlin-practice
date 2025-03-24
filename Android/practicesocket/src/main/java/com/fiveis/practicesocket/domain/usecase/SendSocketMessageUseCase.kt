package com.fiveis.practicesocket.domain.usecase

import com.fiveis.practicesocket.data.remote.dto.SocketMessageDto
import com.fiveis.practicesocket.domain.repository.SocketRepository

class SendSocketMessageUseCase(private val repository: SocketRepository) {
    fun execute(message: SocketMessageDto) {
        repository.sendSocketMessage(message)
    }
}
