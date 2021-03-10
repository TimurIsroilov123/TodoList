package com.example.todolis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.database.TodoEntity
import com.example.todolist.databinding.AddNewDialogBinding
import com.example.todolist.models.TodoViewModelFactory
import com.example.todolist.models.TodosViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddDialog : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_new_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = AddNewDialogBinding.bind(view)

        binding.btAddTask.setOnClickListener {
            val task = binding.etTask.text.toString()
            val action = AddDialogDirections.actionAddDialogToFirstFragment(task)
            findNavController().navigate(action)
        }

    }
}