package com.example.core.interactors

import com.example.core.data.TodoRepository

class GetTodos(private val todoRepository: TodoRepository) {
    suspend operator fun invoke() = todoRepository.getTodos()
}