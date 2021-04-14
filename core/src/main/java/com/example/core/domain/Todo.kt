package com.example.core.domain

data class Todo(
    val id: Long? = null,
    val isDone: Boolean,
    val taskTitle: String,
    val date: String
)
