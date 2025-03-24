package com.fiveis.practicesocket.data.config

import com.fiveis.practicesocket.data.remote.socket.SocketManager
import com.fiveis.practicesocket.data.repository.SocketRepositoryImpl
import com.fiveis.practicesocket.domain.repository.SocketRepository
import com.fiveis.practicesocket.domain.usecase.ConnectSocketUseCase
import com.fiveis.practicesocket.domain.usecase.DisconnectSocketUseCase
import com.fiveis.practicesocket.domain.usecase.SendSocketMessageUseCase

// PieSocket 테스트용 웹소켓 엔드포인트 URL
private const val BASE_URL = "ws://192.168.69.137:8080/websocket"

// SocketManager의 기본 인스턴스
private val defaultSocketManager = SocketManager(BASE_URL)

// 기본 SocketRepository 구현체
val DefaultSocketRepository: SocketRepository = SocketRepositoryImpl(defaultSocketManager)

// 기본 UseCase 인스턴스들
val DefaultConnectSocketUseCase = ConnectSocketUseCase(DefaultSocketRepository)
val DefaultDisconnectSocketUseCase = DisconnectSocketUseCase(DefaultSocketRepository)
val DefaultSendSocketMessageUseCase = SendSocketMessageUseCase(DefaultSocketRepository)
