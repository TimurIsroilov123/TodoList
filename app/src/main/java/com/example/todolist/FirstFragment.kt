package com.example.todolist

import android.os.Bundle
import android.util.Property.of
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.Insets.of
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.Todo
import com.example.todolist.recycler_staff.TodoAdapter
import com.example.todolist.databinding.FragmentFirstBinding
import com.example.todolist.models.TodoViewModelFactory
import com.example.todolist.models.TodosViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.EnumSet.of
import java.util.List.of
import java.util.Map.of
import java.util.Optional.of
import java.util.OptionalDouble.of
import java.util.stream.Collector.of

class FirstFragment : Fragment() {

    private val args: FirstFragmentArgs by navArgs()

    private val viewModel: TodosViewModel by viewModels()

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.myArg.isNotBlank()) {
            viewModel.insertTodo(Todo( false, args.myArg))
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