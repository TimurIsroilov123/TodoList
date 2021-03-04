package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.todolist.App.Companion.todosDB
import com.example.todolist.database.TodoEntity
import com.example.todolist.models.TodoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Repository {

//    suspend fun getAllTodos() = withContext(Dispatchers.IO) {
//        todosDB.todosDao.getTodosObservable()
//    }

//    fun getAllTodos(): Observable<TodoModel> =
//        todosDB.todosDao.getTodosObservable().map { it.toModel() }

//    fun getAllTodos(): LiveData<List<TodoModel>> =
//        todosDB.todosDao.getTodosObservable().map { it.map { todo -> todo.toModel()} }


    suspend fun addNew(todoModel: TodoModel) = withContext(Dispatchers.IO) {
        todosDB.todosDao.insert(todoModel.toEntity())
    }

    private fun TodoModel.toEntity() = TodoEntity(
        isDone = this.isDone,
        taskTitle = this.taskTitle
    )

    fun TodoEntity.toModel() = TodoModel(
        isDone = this.isDone,
        taskTitle = this.taskTitle
    )

}