package com.example.core.data

import com.example.core.domain.Todo
import kotlinx.coroutines.flow.Flow

interface TodoDataSource {
    suspend fun add(todo: Todo)

    suspend  fun readAll(): Flow<List<Todo>>

    suspend fun remove()
}
