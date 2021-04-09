package com.example.todolist.ui.todo.recycler_staff

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.Todo
import com.example.todolist.databinding.ItemTodoBinding

class TodoAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Todo, TodoAdapter.TodoViewHolder>(TodoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

    inner class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val todo = getItem(position)
                        listener.onItemClick(todo)
                    }
                }

                cbDone.setOnClickListener  {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val todo = getItem(position)
                        listener.onCheckBoxClick(todo, cbDone.isChecked)
                    }
                }
            }
        }

        fun onBind(todo: Todo) {
            binding.apply {
                cbDone.isChecked = todo.isDone
                tvTask.text = todo.taskTitle
                tvDate.text = todo.date
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(todo: Todo)
        fun onCheckBoxClick(todo: Todo, isChecked: Boolean)
    }
}