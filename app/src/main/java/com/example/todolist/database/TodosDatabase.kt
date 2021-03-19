package com.example.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [Todo::class], version = 1, exportSchema = false
)
abstract class TodosDataBase : RoomDatabase() {

    abstract fun todosDao(): TodosDAO

    private class TodoDataBaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch {
                    it.todosDao().deleteAll()
                }
            }
        }
    }

    companion object {

        private var INSTANCE: TodosDataBase? = null

        fun create(
            context: Context,
            scope: CoroutineScope
        ): TodosDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodosDataBase::class.java,
                    "todos_database"
                )
                    .addCallback(TodoDataBaseCallback(scope))
                    .build()
                 INSTANCE = instance
                instance
            }
        }
    }
}