package com.example.todolist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodosDAO {
    @Query("select * from todos_table")
    fun getAllTodos(): Flow<List<TodoEntity>>

    @Query("select * from todos_table where id == 1")
    fun getDoneTodos(): Flow<List<TodoEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todoEntity: TodoEntity)

    @Query("delete from todos_table")
    suspend fun deleteAll()

    @Query("delete from todos_table where id == :id")
    suspend fun deleteAt(id: Int)

    @Delete
    suspend fun deleteTodo(todo: TodoEntity)

    @Update
    suspend fun updateTodo(todo: TodoEntity)
}