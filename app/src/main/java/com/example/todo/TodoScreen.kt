package com.example.todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun TodoScreen(
    navController: NavHostController,
    viewModel: TodoViewModel,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val todos by viewModel.todos.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Todo List") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_todo_item") }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Todo Item")
            }
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {

            }
            if (todos.isEmpty()) {
                EmptyTodoList()
            } else {
                TodoList(
                    todos = todos,
                    onTodoItemClicked = { todoItem ->
                        navController.navigate(
                            "edit_todo_item/{todoId}".replace(
                                oldValue = "{todoId}",
                                newValue = "${todoItem.id}"
                            )
                        )
                    },
                    onTodoItemCheckedChanged = { todoItem ->
                        viewModel.updateTodoItem(todoItem.copy(completed = !todoItem.completed))
                    },
                    onTodoDeleteItemClicked = { viewModel.deleteTodoItem(it) }
                )
            }
        }
    )
}

