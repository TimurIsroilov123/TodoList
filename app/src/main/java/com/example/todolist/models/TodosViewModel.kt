package com.example.todolist.models

import android.app.Application
import androidx.lifecycle.*
import com.example.core.domain.Todo
import com.example.todolist.MainViewModel
import com.example.todolist.framework.Interactors
import com.example.todolist.framework.database.TodoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class TodosViewModel(application: Application, interactors: Interactors
) : MainViewModel(application, interactors) {

    var allTodos: Flow<List<Todo>> = flowOf()

    fun getTodosFromDb() =
        viewModelScope.launch(Dispatchers.IO) {
            allTodos = interactors.getTodos()
        }

    fun insertTodo(todo: Todo) =
        viewModelScope.launch(Dispatchers.IO) {
            interactors.addTodo(todo)
        }
}

//class TodosViewModel(private val repository: Repository) : ViewModel() {
//
//    val allTodos: Flow<List<TodoEntity>> = repository.allTodos
//
//    fun insertTodo(todoEntity: TodoEntity) =
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.add(todoEntity)
//        }
//}

object TodoViewModelFactory : ViewModelProvider.Factory {
    lateinit var application: Application

    lateinit var dependencies: Interactors

    fun inject(application: Application, dependencies: Interactors) {
        TodoViewModelFactory.application = application
        TodoViewModelFactory.dependencies = dependencies
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return modelClass.getConstructor(Application::class.java, Interactors::class.java)
            .newInstance(
                application,
                dependencies
            )
    }
}
