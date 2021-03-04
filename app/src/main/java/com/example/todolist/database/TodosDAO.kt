package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodosDAO {

    @Query("Select * From todosTable")
    suspend fun getAll(): List<TodoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todoEntity: TodoEntity)

    @Update
    suspend fun updateTask(todoEntity: TodoEntity)

    @Query("Select * from todosTable")
    fun getTodosObservable() : LiveData<List<TodoEntity>>


//    @Query("Select * from todosTable")
//    fun getTodosObservable() : Observable<TodoEntity>
}