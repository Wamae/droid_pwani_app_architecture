package com.example.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todo.database.TodoItem

@Composable
fun AddTodoItemScreen(
    viewModel: TodoViewModel,
    navigateBack: () -> Unit
) {
    val todoTitle = remember { mutableStateOf("") }
    val todoDescription = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add Todo Item") },
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
                    value = todoTitle.value,
                    onValueChange = { todoTitle.value = it },
                    label = { Text("Title") }
                )
                OutlinedTextField(
                    value = todoDescription.value,
                    onValueChange = { todoDescription.value = it },
                    label = { Text("Description") }
                )
                Button(
                    onClick = {
                        viewModel.addTodoItem(
                            TodoItem(
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
