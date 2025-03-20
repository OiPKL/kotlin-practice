package com.fiveis.practiceapi.domain.repository

import com.fiveis.practiceapi.data.remote.dto.TodoDto

interface JsonRepository {
    suspend fun getTodo(): TodoDto?
    suspend fun postTodo(todo: TodoDto): Boolean
    suspend fun getLocalTodo(): TodoDto?
}