package com.fiveis.practicesocket.data.remote.dto

data class SocketMessageDto(
    val sender: String,
    val jwt: String,
    val cmd: String
)