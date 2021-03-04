package com.example.todolist.models

import android.content.Context
import android.widget.EditText
import androidx.lifecycle.*
import com.example.todolist.App
import com.example.todolist.App.Companion.todosDB
import com.example.todolist.Repository
import com.example.todolist.database.TodoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodosViewModel : ViewModel() {

    val observableTodos: LiveData<List<TodoEntity>> by lazy {
        todosDB.todosDao.getTodosObservable()
    }

    fun addTask(etTask: EditText) {
        viewModelScope.launch(Dispatchers.IO) {
            Repository.addNew(TodoModel(false, etTask.text.toString()))
        }
    }

}

class TodoViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodosViewModel() as T
    }
}


//    fun getTodos(): Observable<TodoModel> {
//        return Repository.getAllTodos()
//    }