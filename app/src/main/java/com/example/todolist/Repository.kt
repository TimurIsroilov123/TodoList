package com.example.todolist

import androidx.annotation.WorkerThread
import com.example.todolist.database.TodoEntity
import com.example.todolist.database.TodosDAO
import kotlinx.coroutines.flow.Flow

class Repository(private val todosDao: TodosDAO) {

   val allTodos: Flow<List<TodoEntity>> = todosDao.getAllWithFLow()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertTodo(todoEntity: TodoEntity) {
        todosDao.insert(todoEntity)
    }
}