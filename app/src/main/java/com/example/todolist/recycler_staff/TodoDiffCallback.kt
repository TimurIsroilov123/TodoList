package com.example.todolist.recycler_staff

import androidx.recyclerview.widget.DiffUtil
import com.example.todolist.framework.database.TodoEntity

class TodoDiffCallback: DiffUtil.ItemCallback<TodoEntity>() {

    override fun areItemsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
        return oldItem.taskTitle == newItem.taskTitle
    }

    override fun areContentsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
        return oldItem == newItem
    }
}