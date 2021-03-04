package com.example.todolist.recycler_staff

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.models.TodoModel

class TodoAdapter : RecyclerView.Adapter<TodoViewHolder>() {

    private val contentData = mutableListOf<TodoModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TodoViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.onBind(contentData[position])
    }

    override fun getItemCount(): Int = contentData.size

    fun update(todoModels: List<TodoModel>) {
        val numberOfRemovedItems = contentData.size
        contentData.clear()
        val positionStart = contentData.size
        contentData.addAll(todoModels)
        notifyItemRangeRemoved(0, numberOfRemovedItems)
        notifyItemRangeInserted(
            positionStart,
            positionStart + todoModels.size
        )
    }
}