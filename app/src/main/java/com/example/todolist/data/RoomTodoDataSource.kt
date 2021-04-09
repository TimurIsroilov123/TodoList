package com.example.todolist.data

import android.content.Context
import com.example.core.data.TodoDataSource
import com.example.core.domain.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomTodoDataSource(context: Context) : TodoDataSource {

    private val todoDao = TodosDataBase.create(context).todosDao()

    override suspend fun add(todo: Todo) =
        todoDao.addTodo(todo.toEntity())

    override suspend fun readAll(): Flow<List<Todo>> =
        todoDao.getAllTodos().map {
            it.map { todoEntity ->
                todoEntity.toTodo()
            }
        }

    override suspend fun getDone(): Flow<List<Todo>> =
        todoDao.getDoneTodos().map {
            it.map { todo ->
                todo.toTodo()
            }
        }

    override suspend fun removeAll() = todoDao.deleteAll()
    override suspend fun removeAt(id: Int) = todoDao.deleteAt(id)
    override suspend fun deleteTodo(todo: Todo) = todoDao.deleteTodo(todo.toEntity())
    override suspend fun update(todo: Todo) = todoDao.updateTodo(todo.toEntity())

    private fun Todo.toEntity() =
        TodoEntity(this.id ?: 0, this.isDone, this.taskTitle, this.date)

    private fun TodoEntity.toTodo() =
        Todo(this.id, this.isDone, this.taskTitle, this.date)
}