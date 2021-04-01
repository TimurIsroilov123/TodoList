package com.example.todolist

import androidx.annotation.WorkerThread
import com.example.todolist.framework.database.TodoEntity
import com.example.todolist.framework.database.TodosDAO
import kotlinx.coroutines.flow.Flow

class Repository(private val todosDao: TodosDAO) {

   val allTodos: Flow<List<TodoEntity>> = todosDao.getAllTodos()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun add(todoEntity: TodoEntity) {
        todosDao.addTodo(todoEntity)
    }
}