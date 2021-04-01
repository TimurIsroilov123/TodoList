package com.example.core.data

import com.example.core.domain.Todo

class TodoRepository(private val dataSource: TodoDataSource) {
    suspend fun addTodo(todo: Todo) = dataSource.add(todo)

    suspend fun getTodos() = dataSource.readAll()

    suspend fun removeTodos() = dataSource.remove()
}