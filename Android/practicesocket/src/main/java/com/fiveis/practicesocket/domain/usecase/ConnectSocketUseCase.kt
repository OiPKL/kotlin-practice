package com.fiveis.practicesocket.domain.usecase

import com.fiveis.practicesocket.domain.repository.SocketRepository

class ConnectSocketUseCase(private val repository: SocketRepository) {
    fun execute() {
        repository.connectSocket()
    }
}
