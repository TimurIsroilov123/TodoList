package com.example.todolist.recycler_staff

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTodoBinding
import com.example.todolist.models.TodoModel

class TodoViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    private var binding = ItemTodoBinding.bind(view)


    fun onBind(todos: TodoModel) {
        todos.apply {
            binding.cbDone.isChecked = isDone
            binding.tvTask.text = taskTitle
        }
    }
}