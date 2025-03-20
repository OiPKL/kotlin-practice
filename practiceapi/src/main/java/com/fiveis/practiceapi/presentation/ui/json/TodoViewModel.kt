package com.fiveis.practiceapi.presentation.ui.json

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.fiveis.practiceapi.data.remote.dto.TodoDto
import com.fiveis.practiceapi.data.repository.JsonRepositoryImpl
import com.fiveis.practiceapi.domain.usecase.GetTodoUseCase
import com.fiveis.practiceapi.domain.usecase.PostTodoUseCase
import kotlinx.coroutines.launch

data class TodoUiState(
    val resultText: String = ""
)

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    // repository 및 usecase 초기화
    private val repository = JsonRepositoryImpl(application.applicationContext)
    private val getTodoUseCase = GetTodoUseCase(repository)
    private val postTodoUseCase = PostTodoUseCase(repository)

    var uiState = mutableStateOf(TodoUiState())
        private set

    // POST 요청용 입력 필드 상태
    var userIdInput by mutableStateOf("")
    var titleInput by mutableStateOf("")
    var completedInput by mutableStateOf("false")

    fun onGetTodo() {
        viewModelScope.launch {
            val todo = getTodoUseCase.invoke()
            uiState.value = if (todo != null) {
                val formatted = """
                GET
                User ID: ${todo.userId}
                ID: ${todo.id}
                Title: ${todo.title}
                Completed: ${todo.completed}
            """.trimIndent()
                TodoUiState(resultText = formatted)
            } else {
                TodoUiState(resultText = "GET: 실패")
            }
        }
    }

    fun onPostTodo() {
        viewModelScope.launch {
            val userId = userIdInput.toIntOrNull() ?: 0
            val title = titleInput
            val completed = completedInput.toBoolean()
            val todo = TodoDto(userId = userId, title = title, completed = completed)
            val success = postTodoUseCase.invoke(todo)
            uiState.value = if (success) {
                TodoUiState(resultText = "POST: 성공")
            } else {
                TodoUiState(resultText = "POST: 실패")
            }
        }
    }

    fun onLocalTodo() {
        viewModelScope.launch {
            val todo = repository.getLocalTodo()
            uiState.value = if (todo != null) {
                val formatted = """
                LOCAL
                User ID: ${todo.userId}
                ID: ${todo.id}
                Title: ${todo.title}
                Completed: ${todo.completed}
            """.trimIndent()
                TodoUiState(resultText = formatted)
            } else {
                TodoUiState(resultText = "Local: 저장된 데이터가 없습니다.")
            }
        }
    }
}