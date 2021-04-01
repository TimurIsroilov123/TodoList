package com.example.todolist.framework

import android.content.Context
import com.example.core.data.TodoDataSource
import com.example.core.domain.Todo
import com.example.todolist.framework.database.TodoEntity
import com.example.todolist.framework.database.TodosDataBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomTodoDataSource(context: Context): TodoDataSource {

    private val todoDao = TodosDataBase.create(context).todosDao()

    override suspend fun add(todo: Todo) =
        todoDao.addTodo(todo.toEntity())

    override suspend fun readAll(): Flow<List<Todo>> =
        todoDao.getAllTodos().map {
            it.map { todoEntity ->
                todoEntity.toTodo()
            }
        }

    override suspend fun remove() = todoDao.deleteAll()

    private fun Todo.toEntity() =
        TodoEntity(null, this.isDone, this.taskTitle)

    private fun TodoEntity.toTodo() =
        Todo(this.isDone, this.taskTitle)
}