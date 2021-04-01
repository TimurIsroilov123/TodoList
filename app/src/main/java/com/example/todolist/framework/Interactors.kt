package com.example.todolist.framework

import com.example.core.interactors.AddTodo
import com.example.core.interactors.GetTodos

data class Interactors(
    val addTodo: AddTodo,
    val getTodos: GetTodos
)