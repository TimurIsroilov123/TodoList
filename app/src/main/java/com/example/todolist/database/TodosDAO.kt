package com.example.todolist.database

import androidx.lifecycle.LiveData
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

//    @Update
//    suspend fun updateTask(todoEntity: TodoEntity)
//
//    @Query("Select * from todos_table")
//    fun getTodosObservable() : LiveData<List<TodoEntity>>


//    @Query("Select * from todosTable")
//    fun getTodosObservable() : Observable<TodoEntity>
}