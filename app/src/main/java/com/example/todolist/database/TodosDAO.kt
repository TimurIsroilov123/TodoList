package com.example.todolist.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodosDAO {
    @Query("select * from todos_table")
    fun getAllTodos(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo: Todo)

    @Query("delete from todos_table")
    suspend fun deleteAll()
}