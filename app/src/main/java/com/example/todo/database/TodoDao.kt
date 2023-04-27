package com.example.todo.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    // @Insert(onConflict = OnConflictStrategy.REPLACE)
    // suspend fun addTodoItem(todoItem: TodoItem)

    @Query("SELECT * FROM todos")
    fun getAllTodoItems(): Flow<List<TodoItem>>

    @Query("SELECT * FROM todos WHERE id = :id")
    fun getTodoItemById(id: Int): Flow<TodoItem?>

    @Delete
    suspend fun delete(todoItem: TodoItem)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoItem: TodoItem)

    @Update
    suspend fun update(todoItem: TodoItem)
}
