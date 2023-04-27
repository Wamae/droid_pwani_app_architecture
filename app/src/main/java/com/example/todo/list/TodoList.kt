package com.example.todo

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import com.example.todo.database.TodoItem

//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.runtime.Composable

@Composable
fun TodoList(
    todos: List<TodoItem>,
    onTodoItemClicked: (TodoItem) -> Unit,
    onTodoItemCheckedChanged: (TodoItem) -> Unit,
    onTodoDeleteItemClicked: (TodoItem) -> Unit
) {
    LazyColumn {
        items(todos){ todo->
            TodoListItem(
                todoItem = todo,
                onTodoItemClick = { onTodoItemClicked(todo) },
                onTodoItemCheckedChanged = { onTodoItemCheckedChanged(todo) },
                onTodoDeleteItemClicked = { onTodoDeleteItemClicked(todo) }
            )
        }
    }
}
