package com.example.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TodoEntity::class], version = 1
)
abstract class TodosDataBase : RoomDatabase() {
    abstract val todosDao: TodosDAO

    companion object {
        fun create(appContext: Context): TodosDataBase = Room.databaseBuilder(
            appContext,
            TodosDataBase::class.java,
            "todos.db"
        ).fallbackToDestructiveMigration().build()
    }
}