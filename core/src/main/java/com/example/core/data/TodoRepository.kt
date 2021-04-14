package com.example.core.data

import com.example.core.domain.Todo

class TodoRepository(private val dataSource: TodoDataSource) {
    suspend fun addTodo(todo: Todo) = dataSource.add(todo)

    suspend fun getTodos() = dataSource.readAll()

    suspend fun getDone() = dataSource.getDone()

    suspend fun removeTodos() = dataSource.removeAll()

    suspend fun removeAt(id: Int) = dataSource.removeAt(id)

    suspend fun deleteTodo(todo: Todo) = dataSource.deleteTodo(todo)

    suspend fun updateTodo(todo: Todo) = dataSource.update(todo)
}