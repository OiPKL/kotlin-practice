package com.fiveis.practiceapi.data.remote.dto

data class TodoDto(
    val userId: Int,
    val id: Int? = null,  // GET 응답에는 id가 포함되며, POST 요청 시에는 생략될 수 있음
    val title: String,
    val completed: Boolean
)