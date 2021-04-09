package com.example.todolist.ui

import android.app.Application
import com.example.core.data.TodoRepository
import com.example.core.interactors.*
import com.example.todolist.data.Interactors
import com.example.todolist.data.RoomTodoDataSource
import com.example.todolist.data.TodosDataBase
import com.example.todolist.models.TodoViewModelFactory

class App : Application() {

    val database by lazy { TodosDataBase.create(this) }

    override fun onCreate() {
        super.onCreate()

        val todoRepository = TodoRepository(RoomTodoDataSource(this))

        TodoViewModelFactory.inject(
        this,
            Interactors(
                AddTodo(todoRepository),
                GetTodos(todoRepository),
                DeleteAll(todoRepository),
                DeleteAt(todoRepository),
                DeleteTodo(todoRepository),
                UpdateTodo(todoRepository),
                GetDone(todoRepository)
            )
        )

    }
}