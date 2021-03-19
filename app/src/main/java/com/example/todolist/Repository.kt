package com.example.todolist

import androidx.annotation.WorkerThread
import com.example.todolist.database.Todo
import com.example.todolist.database.TodosDAO
import kotlinx.coroutines.flow.Flow

class Repository(private val todosDao: TodosDAO) {

   val allTodos: Flow<List<Todo>> = todosDao.getAllTodos()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertTodo(todo: Todo) {
        todosDao.insert(todo)
    }
}