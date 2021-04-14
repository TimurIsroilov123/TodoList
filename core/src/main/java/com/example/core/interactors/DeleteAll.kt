package com.example.core.interactors

import com.example.core.data.TodoRepository
import com.example.core.domain.Todo

class DeleteAll(private val todoRepository: TodoRepository) {
    suspend operator fun invoke() =
        todoRepository.removeTodos()
}