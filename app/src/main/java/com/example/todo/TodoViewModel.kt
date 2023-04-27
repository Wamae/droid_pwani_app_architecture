package com.example.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.database.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos

    init {
        viewModelScope.launch {
            repository.getTodos().collect { todos ->
                _todos.value = todos
            }
        }
    }

    fun addTodoItem(todoItem: TodoItem) {
        viewModelScope.launch {
            repository.addTodoItem(todoItem)
        }
    }

    fun updateTodoItem(todoItem: TodoItem) {
        viewModelScope.launch {
            repository.updateTodoItem(todoItem)
        }
    }

    fun deleteTodoItem(todoItem: TodoItem) {
        viewModelScope.launch {
            repository.deleteTodoItem(todoItem)
        }
    }

    private val _todo = MutableStateFlow(TodoItem(title = "", description = ""))
    val todo: StateFlow<TodoItem> = _todo

    fun getTodoItem(todoItemId: Int) {
        viewModelScope.launch {
            repository.getTodoItemById(todoItemId).collect{
                _todo.value = it ?: TodoItem(title = "", description = "")
            }
        }
    }

    fun onTitleChange(title: String) {
        _todo.value.title = title
    }

    fun onDescriptionChange(description: String) {
        _todo.value.description = description
    }
}
