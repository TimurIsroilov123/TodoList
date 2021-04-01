package com.example.todolist.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [TodoEntity::class],
    version = 1,
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
                    .build()
                 INSTANCE = instance
                instance
            }
        }
    }
}