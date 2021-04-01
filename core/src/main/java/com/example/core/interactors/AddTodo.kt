package com.example.core.interactors

import com.example.core.data.TodoRepository
import com.example.core.domain.Todo

class AddTodo(private val todoRepository: TodoRepository) {
    suspend operator fun invoke(todo: Todo) =
        todoRepository.addTodo(todo)
}