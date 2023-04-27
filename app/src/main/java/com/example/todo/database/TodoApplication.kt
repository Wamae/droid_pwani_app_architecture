package com.example.todo.database

import android.app.Application
import com.example.todo.TodoRepository
import com.example.todo.TodoViewModel

class TodoApplication : Application() {
    private val todoRepository by lazy { TodoRepository(this) }
    val todoViewModel by lazy { TodoViewModel(todoRepository) }
}
