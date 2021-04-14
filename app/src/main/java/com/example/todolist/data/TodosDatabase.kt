package com.example.todolist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TodoEntity::class],
    version = 3,
    exportSchema = false
)
abstract class TodosDataBase : RoomDatabase() {

    abstract fun todosDao(): TodosDAO

    companion object {

        private var INSTANCE: TodosDataBase? = null

        fun create(
            context: Context
        ): TodosDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodosDataBase::class.java,
                    "todos_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                 INSTANCE = instance
                instance
            }
        }
    }
}