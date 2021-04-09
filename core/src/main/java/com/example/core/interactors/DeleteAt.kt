package com.example.core.interactors

import com.example.core.data.TodoRepository
import com.example.core.domain.Todo

class DeleteAt(private val todoRepository: TodoRepository) {
        suspend operator fun invoke(id: Int) =
            todoRepository.removeAt(id)
}