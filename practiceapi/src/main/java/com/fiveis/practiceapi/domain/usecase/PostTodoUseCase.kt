package com.fiveis.practiceapi.domain.usecase

import com.fiveis.practiceapi.data.remote.dto.TodoDto
import com.fiveis.practiceapi.domain.repository.JsonRepository

class PostTodoUseCase(private val repository: JsonRepository) {
    suspend operator fun invoke(todo: TodoDto): Boolean {
        return repository.postTodo(todo)
    }
}