package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.Repository.toModel
import com.example.todolist.recycler_staff.TodoAdapter
import com.example.todolist.databinding.FragmentFirstBinding
import com.example.todolist.models.TodoModel
import com.example.todolist.models.TodoViewModelFactory
import com.example.todolist.models.TodosViewModel

class FirstFragment : Fragment() {

    private val todoDataSet = listOf(
        TodoModel(false, "Algorithms"),
        TodoModel(false, "Homework"),
        TodoModel(false, "PE"),
        TodoModel(true, "Take a rest")
    )

    private val viewModel by viewModels<TodosViewModel> {
        TodoViewModelFactory()
    }

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val todoAdapter: TodoAdapter by lazy { TodoAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_addDialog)
        }
        binding.rvTodoList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.observableTodos.observe(this.viewLifecycleOwner) {
            todoAdapter.update(it.map { todo -> todo.toModel()})
        }

        binding.rvTodoList.adapter = todoAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}