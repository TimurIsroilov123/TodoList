package com.example.todolist

import android.app.Application
import com.example.todolist.database.TodosDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { TodosDataBase.create(this, applicationScope) }
    val repository by lazy { Repository(database.todosDao()) }
}