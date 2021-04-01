package com.example.todolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.todolist.framework.Interactors

open class MainViewModel(
    application: Application,
    protected val interactors: Interactors
): AndroidViewModel(application) {

    protected val application: App = getApplication()
}