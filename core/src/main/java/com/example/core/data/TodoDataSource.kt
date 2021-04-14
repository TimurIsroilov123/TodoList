package com.example.core.data

import com.example.core.domain.Todo
import kotlinx.coroutines.flow.Flow

interface TodoDataSource {
    suspend fun add(todo: Todo)

    suspend fun readAll(): Flow<List<Todo>>

    suspend fun getDone(): Flow<List<Todo>>

    suspend fun removeAll()

    suspend fun removeAt(id: Int)

    suspend fun deleteTodo(todo: Todo)

    suspend fun update(todo: Todo)
}
