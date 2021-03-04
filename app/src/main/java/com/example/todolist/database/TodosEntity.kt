package com.example.todolist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "todosTable",
    indices = [Index("id")]
)
class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    @ColumnInfo(name = "isDone")
    val isDone: Boolean,

    @ColumnInfo(name = "taskTitle")
    val taskTitle: String
)