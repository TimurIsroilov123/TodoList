package com.example.todolist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "todos_table",
    indices = [Index("id")]
)
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    @ColumnInfo(name = "is_done")
    val isDone: Boolean,

    @ColumnInfo(name = "task_title")
    val taskTitle: String
)