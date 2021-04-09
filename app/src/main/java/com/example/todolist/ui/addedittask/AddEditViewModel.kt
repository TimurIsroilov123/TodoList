package com.example.todolist.ui.addedittask

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.todolist.ui.MainViewModel
import com.example.todolist.data.Interactors
import com.example.todolist.data.TodoEntity

class AddEditViewModel(

    private val state: SavedStateHandle
) : ViewModel() {

    val task = state.get<TodoEntity>("task")
    var taskName = state.get<String>("taskName") ?: task?.taskTitle ?: ""
        set(value) {
            field = value
            state.set("taskName", value)
        }
}