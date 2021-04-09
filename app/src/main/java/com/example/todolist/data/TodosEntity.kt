package com.example.todolist.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(
    tableName = "todos_table",
    indices = [Index("id")]
)
@Parcelize
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "is_done")
    val isDone: Boolean,

    @ColumnInfo(name = "task_title")
    val taskTitle: String,

    @ColumnInfo(name = "date")
    val date: String
): Parcelable