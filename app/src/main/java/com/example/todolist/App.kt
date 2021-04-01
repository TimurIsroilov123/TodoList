package com.example.todolist

import android.app.Application
import com.example.core.data.TodoRepository
import com.example.core.interactors.AddTodo
import com.example.core.interactors.GetTodos
import com.example.todolist.framework.Interactors
import com.example.todolist.framework.RoomTodoDataSource
import com.example.todolist.framework.database.TodosDataBase
import com.example.todolist.models.TodoViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {

    val database by lazy { TodosDataBase.create(this) }

    override fun onCreate() {
        super.onCreate()

        val todoRepository = TodoRepository(RoomTodoDataSource(this))

        TodoViewModelFactory.inject(
        this,
            Interactors(
                AddTodo(todoRepository),
                GetTodos(todoRepository)
            )
        )

    }
}