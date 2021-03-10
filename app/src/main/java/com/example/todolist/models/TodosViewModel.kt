package com.example.todolist.models

import androidx.lifecycle.*
import com.example.todolist.Repository
import com.example.todolist.database.TodoEntity
import kotlinx.coroutines.launch

class TodosViewModel(private val repository: Repository) : ViewModel() {

    val allTodos: LiveData<List<TodoEntity>> = repository.allTodos.asLiveData()

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
