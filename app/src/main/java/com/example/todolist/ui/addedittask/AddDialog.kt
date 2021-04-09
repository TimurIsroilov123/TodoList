package com.example.todolist.ui.addedittask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core.domain.Todo
import com.example.todolist.R
import com.example.todolist.databinding.AddNewDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.*

class AddDialog : BottomSheetDialogFragment() {

    private val viewModel: AddEditViewModel by viewModels()

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

        binding.apply {
            etTask.setText(viewModel.taskName)
            ivAddTask.setOnClickListener {
                val action = AddDialogDirections.actionAddDialogToFirstFragment(etTask.text.toString())
//                findNavController().popBackStack()
                findNavController().navigate(action)
            }
        }

    }
}