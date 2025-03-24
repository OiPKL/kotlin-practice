package com.fiveis.practiceapi.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private const val DATASTORE_NAME = "todo_datastore"
val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

object TodoDataStore {
    private val TODO_KEY = stringPreferencesKey("todo_json")

    suspend fun saveTodo(context: Context, todoJson: String) {
        context.dataStore.edit { preferences ->
            preferences[TODO_KEY] = todoJson
        }
    }

    suspend fun getTodo(context: Context): String? {
        val preferences = context.dataStore.data.first()
        return preferences[TODO_KEY]
    }
}