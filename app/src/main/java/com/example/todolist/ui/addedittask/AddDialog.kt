package com.example.todolist.ui.addedittask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.core.domain.Todo
import com.example.todolist.R
import com.example.todolist.databinding.AddNewDialogBinding
import com.example.todolist.models.TodoViewModelFactory
import com.example.todolist.models.TodosViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.*

class AddDialog : BottomSheetDialogFragment() {

    private val viewModel: TodosViewModel by viewModels {
        TodoViewModelFactory
    }

    private val args: AddDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_new_dialog, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
        val currentDate = sdf.format(Date())

        val binding = AddNewDialogBinding.bind(view)
        viewModel.task =
            Todo(
                args.task?.id ?: null,
                args.task?.isDone ?: false,
                args.task?.taskTitle ?: "",
                args.task?.date ?: currentDate
            )

        binding.apply {
            etTask.setText(viewModel.task?.taskTitle)
            ivAddTask.setOnClickListener {
                if (args.task == null) {
                    viewModel.addTodo(Todo(null, false, etTask.text.toString(), currentDate))
                } else {
                    viewModel.apply {
                        updateTodo(task!!.copy(taskTitle = etTask.text.toString(), date = currentDate))
                    }
                }
                findNavController().popBackStack()
            }
        }

    }
}