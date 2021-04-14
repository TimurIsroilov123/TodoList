package com.example.todolist.models

import android.app.Application
import androidx.lifecycle.*
import com.example.core.domain.Todo
import com.example.todolist.ui.MainViewModel
import com.example.todolist.data.Interactors
import com.example.todolist.data.TodoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow

class TodosViewModel(
    application: Application, interactors: Interactors
) : MainViewModel(application, interactors) {

    var allTodos: Flow<List<Todo>> = flowOf()
    var doneTodos: Flow<List<Todo>> = flowOf()
    var inProgressTodos: Flow<List<Todo>> = flowOf()

    var task: Todo? = null

    private val tasksEventChannel = Channel<TasksEvent>()
    val tasksEvent = tasksEventChannel.receiveAsFlow()

    fun getTodosFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            allTodos = interactors.getTodos()
        }
    }

    fun getDoneTodos() =
        viewModelScope.launch {
            doneTodos = allTodos.map {
                it.filter { todos ->
                    todos.isDone
                }
            }
        }

    fun getInProgressTodos() =
        viewModelScope.launch {
            inProgressTodos = allTodos.map {
                it.filter { todos ->
                    !todos.isDone
                }
            }
        }

    fun delete() = viewModelScope.launch(Dispatchers.IO) {
        interactors.removeTodos
    }

    fun addTodo(todo: Todo) = viewModelScope.launch {
        interactors.addTodo(todo)
    }

    fun onTodoSelected(todo: Todo) = viewModelScope.launch {
        tasksEventChannel.send(TasksEvent.NavigateToEditTaskScreen(todo))
    }

    fun onTodoCheckedChanged(todo: Todo, isChecked: Boolean) =
        viewModelScope.launch {
            interactors.updateTodo(todo.copy(isDone = isChecked))
        }

    fun onTodoSwiped(todo: Todo) = viewModelScope.launch {
        interactors.deleteTodo(todo)
    }

    fun updateTodo(todo: Todo) = viewModelScope.launch {
        interactors.updateTodo(todo)
    }

    sealed class TasksEvent {
        object NavigateToAddTaskScreen : TasksEvent()
        data class NavigateToEditTaskScreen(val todo: Todo) : TasksEvent()
    }
}

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
