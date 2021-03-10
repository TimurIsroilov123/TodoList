package com.example.todolist.recycler_staff

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.database.TodoEntity
import com.example.todolist.databinding.ItemTodoBinding

class TodoAdapter : ListAdapter<TodoEntity, TodoAdapter.TodoViewHolder>(TodosComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TodoViewHolder {
        return TodoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.onBind(getItem(position).taskTitle)
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

    class TodosComparator : DiffUtil.ItemCallback<TodoEntity>() {
        override fun areItemsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
            return oldItem.taskTitle == newItem.taskTitle
        }
    }
}