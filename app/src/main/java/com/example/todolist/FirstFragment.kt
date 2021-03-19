package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.database.Todo
import com.example.todolist.recycler_staff.TodoAdapter
import com.example.todolist.databinding.FragmentFirstBinding
import com.example.todolist.models.TodoViewModelFactory
import com.example.todolist.models.TodosViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {

    private val args: FirstFragmentArgs by navArgs()

    private val viewModel: TodosViewModel by viewModels {
        TodoViewModelFactory((activity?.application as App).repository)
    }

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val todoAdapter: TodoAdapter by lazy { TodoAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.myArg != "") {
            viewModel.insertTodo(Todo(id = null, false, args.myArg))
        } else {
            Toast.makeText(
                requireContext(),
                "Please add at least one character",
                Toast.LENGTH_SHORT
            )
                .show()
        }

        binding.rvTodoList.adapter = todoAdapter
        binding.rvTodoList.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.allTodos.collect {
                todoAdapter.submitList(it)
            }
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_addDialog)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}