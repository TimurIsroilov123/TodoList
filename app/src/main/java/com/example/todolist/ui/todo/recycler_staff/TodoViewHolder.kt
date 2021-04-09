package com.example.todolist.ui.todo.recycler_staff

//class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    private val binding = ItemTodoBinding.bind(itemView)
//
//    init {
//        binding.apply {
//            root.setOnClickListener {
//                val position = adapterPosition
//                if(position != RecyclerView.NO_POSITION) {
//                    val todo = getItem(position)
//
//                }
//            }
//        }
//    }
//
//    fun onBind(todo: Todo) {
//        binding.tvTask.text = todo.taskTitle
//        binding.tvDate.text = todo.date
//    }
//
//    companion object {
//        fun create(parent: ViewGroup): TodoViewHolder {
//            val view: View = LayoutInflater.from(parent.context)
//                .inflate(R.layout.item_todo, parent, false)
//            return TodoViewHolder(view)
//        }
//    }
//}