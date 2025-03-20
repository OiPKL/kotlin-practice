package com.fiveis.practiceapi.data.repository

import android.content.Context
import com.fiveis.practiceapi.data.local.datastore.TodoDataStore
import com.fiveis.practiceapi.data.remote.api.JsonApi
import com.fiveis.practiceapi.data.remote.dto.TodoDto
import com.fiveis.practiceapi.domain.repository.JsonRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JsonRepositoryImpl(private val context: Context) : JsonRepository {

    private val api: JsonApi

    init {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(JsonApi::class.java)
    }

    override suspend fun getTodo(): TodoDto? {
        val response = api.getTodo()
        if (response.isSuccessful) {
            response.body()?.let { todo ->
                // 저장: Retrofit으로 받은 TodoDto를 Gson으로 JSON 문자열로 변환 후 DataStore에 저장
                val json = com.google.gson.Gson().toJson(todo)
                TodoDataStore.saveTodo(context, json)
                return todo
            }
        }
        return null
    }

    override suspend fun postTodo(todo: TodoDto): Boolean {
        val response = api.postTodo(todo)
        return response.code() == 201
    }

    override suspend fun getLocalTodo(): TodoDto? {
        val json = TodoDataStore.getTodo(context)
        return if (json != null) {
            com.google.gson.Gson().fromJson(json, TodoDto::class.java)
        } else null
    }
}