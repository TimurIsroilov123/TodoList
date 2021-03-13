package com.example.todolist.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodosDAO {

    @Query("Select * From todos_table")
    fun getAllWithFLow(): Flow<List<TodoEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todoEntity: TodoEntity)

    @Query("Delete From todos_table")
    suspend fun deleteAll()
}