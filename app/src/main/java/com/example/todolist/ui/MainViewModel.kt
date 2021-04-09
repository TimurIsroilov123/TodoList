package com.example.todolist.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.todolist.data.Interactors

open class MainViewModel(
    application: Application,
    protected val interactors: Interactors
): AndroidViewModel(application) {

    protected val application: App = getApplication()
}