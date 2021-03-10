package com.example.todolist

import androidx.annotation.WorkerThread
import com.example.todolist.database.TodoEntity
import com.example.todolist.database.TodosDAO
import com.example.todolist.models.TodoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class Repository(private val todosDao: TodosDAO) {

   val allTodos: Flow<List<TodoEntity>> = todosDao.getAllWithFLow()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertTodo(todoEntity: TodoEntity) {
        todosDao.insert(todoEntity)
    }
}