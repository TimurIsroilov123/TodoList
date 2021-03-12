package com.example.todolist.models

import androidx.lifecycle.*
import com.example.todolist.Repository
import com.example.todolist.database.TodoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TodosViewModel(private val repository: Repository) : ViewModel() {

    val allTodos: Flow<List<TodoEntity>> = repository.allTodos

    fun insertTodo(todoEntity: TodoEntity) = viewModelScope.launch {
        repository.insertTodo(todoEntity)
    }
}

class TodoViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return TodosViewModel(repository) as T
    }
}
