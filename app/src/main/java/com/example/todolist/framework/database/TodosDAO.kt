package com.example.todolist.framework.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodosDAO {
    @Query("select * from todos_table")
    fun getAllTodos(): Flow<List<TodoEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todoEntity: TodoEntity)

    @Query("delete from todos_table")
    suspend fun deleteAll()
}