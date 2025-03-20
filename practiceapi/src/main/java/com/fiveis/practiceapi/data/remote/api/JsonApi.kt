package com.fiveis.practiceapi.data.remote.api

import com.fiveis.practiceapi.data.remote.dto.TodoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface JsonApi {
    @GET("todos/10")
    suspend fun getTodo(): Response<TodoDto>

    @POST("todos")
    suspend fun postTodo(@Body todo: TodoDto): Response<TodoDto>
}