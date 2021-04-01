package com.example.todolist.recycler_staff

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.framework.database.TodoEntity
import com.example.todolist.databinding.ItemTodoBinding

class TodoAdapter : ListAdapter<TodoEntity, TodoAdapter.TodoViewHolder>(TodoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TodoViewHolder {
        return TodoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item.taskTitle)
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTodoBinding.bind(itemView)

        fun onBind(task: String?) {
            binding.tvTask.text = task
        }

        companion object {
            fun create(parent: ViewGroup): TodoViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_todo, parent, false)
                return TodoViewHolder(view)
            }
        }
    }
}