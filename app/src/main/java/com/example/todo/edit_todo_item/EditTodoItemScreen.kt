package com.example.todo

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todo.database.TodoItem

@Composable
fun EditTodoItemScreen(
    todoItemId: Int,
    viewModel: TodoViewModel,
    navigateBack: () -> Unit
) {
    // TODO: Improve this code later
    LaunchedEffect(Unit) {
        viewModel.getTodoItem(todoItemId)
    }
    val todo by viewModel.todo.collectAsState()
    val todoTitle = remember { mutableStateOf(todo.title) }
    val todoDescription = remember { mutableStateOf(todo.description) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Edit Todo Item") },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = todo.title,
                    onValueChange = {
                        todoTitle.value = it
                        Log.e("EditTodoItemScreen", "onValueChange: $it")
                        Log.e("EditTodoItemScreen", "onValueChange: ${todoTitle.value }")
                        viewModel.onTitleChange(it)
                                    },
                    label = { Text("Title") }
                )
                OutlinedTextField(
                    value = todoDescription.value,
                    onValueChange = {
                        viewModel.onDescriptionChange(it)
                        todoDescription.value = it },
                    label = { Text("Description") }
                )
                Button(
                    onClick = {
                        viewModel.updateTodoItem(
                            TodoItem(
                                id = todoItemId,
                                title = todoTitle.value,
                                description = todoDescription.value,
                                createdTime = System.currentTimeMillis()
                            )
                        )
                        navigateBack()
                    },
                    enabled = todoTitle.value.isNotBlank()
                ) {
                    Text(text = "Save")
                }
            }
        }
    )
}
