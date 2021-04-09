package com.example.todolist.data

import com.example.core.interactors.*

data class Interactors(
    val addTodo: AddTodo,
    val getTodos: GetTodos,
    val removeTodos: DeleteAll,
    val deleteAt: DeleteAt,
    val deleteTodo: DeleteTodo,
    val updateTodo: UpdateTodo,
    val getDone: GetDone
)