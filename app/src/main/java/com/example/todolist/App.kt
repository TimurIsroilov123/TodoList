package com.example.todolist

import android.app.Application
import com.example.todolist.database.TodosDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        todosDB = TodosDataBase.create(this)
    }

    companion object {
        lateinit var todosDB: TodosDataBase
    }
}