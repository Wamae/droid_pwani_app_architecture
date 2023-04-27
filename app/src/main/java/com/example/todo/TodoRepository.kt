package com.example.todo

import android.content.Context
import com.example.todo.database.TodoDatabase
import com.example.todo.database.TodoItem
import kotlinx.coroutines.flow.Flow

class TodoRepository(context: Context) {
    private val database by lazy { TodoDatabase.getInstance(context) }
    private val todoDao by lazy { database.todoDao() }

    fun getTodos(): Flow<List<TodoItem>> {
        return todoDao.getAllTodoItems()
    }

    suspend fun addTodoItem(todoItem: TodoItem) {
        todoDao.insert(todoItem)
    }

    suspend fun updateTodoItem(todoItem: TodoItem) {
        todoDao.update(todoItem)
    }

    suspend fun deleteTodoItem(todoItem: TodoItem) {
        todoDao.delete(todoItem)
    }

    fun getTodoItemById(todoItemId: Int): Flow<TodoItem?> {
        return todoDao.getTodoItemById(todoItemId)
    }
}
