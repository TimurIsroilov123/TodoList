package com.example.todolist.ui.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.Todo
import com.example.todolist.R
import com.example.todolist.data.TodoEntity
import com.example.todolist.ui.todo.recycler_staff.TodoAdapter
import com.example.todolist.databinding.FragmentFirstBinding
import com.example.todolist.models.TodoViewModelFactory
import com.example.todolist.models.TodosViewModel
import com.example.todolist.util.exhaustive
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FirstFragment : Fragment(R.layout.fragment_first), TodoAdapter.OnItemClickListener {

    private val viewModel: TodosViewModel by viewModels {
        TodoViewModelFactory
    }
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val todoAdapter: TodoAdapter by lazy { TodoAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.getTodosFromDb()

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            getDoneTodos()
            getInProgressTodos()
        }

        binding.apply {
            rvTodoList.adapter = todoAdapter
            rvTodoList.layoutManager = LinearLayoutManager(requireContext())

            lifecycleScope.launch(Dispatchers.Main) {
                viewModel.allTodos.collect {
                    todoAdapter.submitList(it)
                }
            }

            floatingActionButton.setOnClickListener {
                sendTask = null
                findNavController().navigate(R.id.action_FirstFragment_to_addDialog)
            }

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val task = todoAdapter.currentList[viewHolder.adapterPosition]
                    viewModel.onTodoSwiped(task)
                }
            }).attachToRecyclerView(rvTodoList)



            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                viewModel.tasksEvent.collect { event ->
                    when (event) {
                        is TodosViewModel.TasksEvent.NavigateToEditTaskScreen -> {
                            val action =
                                FirstFragmentDirections.actionFirstFragmentToAddDialog(
                                    TodoEntity(
                                        event.todo.id!!,
                                        event.todo.isDone,
                                        event.todo.taskTitle,
                                        event.todo.date
                                    )
                                )
                            sendTask = event.todo
                            findNavController().navigate(action)
                        }
                        is TodosViewModel.TasksEvent.NavigateToAddTaskScreen -> {
                            val action = FirstFragmentDirections.actionFirstFragmentToAddDialog()
                            findNavController().navigate(action)
                        }
                    }.exhaustive
                }
            }

            navigationView.setOnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.all -> {
                        lifecycleScope.launch(Dispatchers.Main) {
                            viewModel.allTodos.collect {
                                todoAdapter.submitList(it)
                            }
                        }
                    }

                    R.id.done -> {
                        lifecycleScope.launch(Dispatchers.Main) {
                            viewModel.doneTodos.collect {
                                todoAdapter.submitList(it)
                            }
                        }
                    }

                    R.id.in_process -> {
                        lifecycleScope.launch(Dispatchers.Main) {
                            viewModel.inProgressTodos.collect {
                                todoAdapter.submitList(it)
                            }
                        }
                    }
                }
                true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(todo: Todo) {
        viewModel.onTodoSelected(todo)
    }

    override fun onCheckBoxClick(todo: Todo, isChecked: Boolean) {
        viewModel.onTodoCheckedChanged(todo, isChecked)
    }

    companion object {
        var sendTask: Todo? = null
    }
}

