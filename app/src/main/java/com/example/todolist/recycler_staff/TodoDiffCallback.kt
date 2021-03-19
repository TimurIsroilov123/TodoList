package com.example.todolist.recycler_staff

import androidx.recyclerview.widget.DiffUtil
import com.example.todolist.database.Todo

class TodoDiffCallback: DiffUtil.ItemCallback<Todo>() {

    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.taskTitle == newItem.taskTitle
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}