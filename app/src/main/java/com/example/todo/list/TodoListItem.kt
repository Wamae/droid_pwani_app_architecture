package com.example.todo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todo.database.TodoItem

@Composable
fun TodoListItem(
    todoItem: TodoItem,
    onTodoItemClick: (TodoItem) -> Unit,
    onTodoItemCheckedChanged: (TodoItem) -> Unit,
    onTodoDeleteItemClicked: (TodoItem) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { onTodoItemClick(todoItem) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth().fillMaxHeight()
        ) {
            Checkbox(
                checked = todoItem.completed,
                onCheckedChange = { onTodoItemCheckedChanged(todoItem) },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Column(modifier = Modifier
                .weight(1f).padding(all = 10.dp)
                .fillMaxWidth().fillMaxHeight(),
            ) {
                Text(
                    text = todoItem.title,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(start = 16.dp).fillMaxHeight()
                )
                Spacer(Modifier.size(4.dp))
                Text(
                    text = todoItem.description,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
            }
            Icon(
                Icons.Default.Delete,
                // color = MaterialTheme.colors.error,
                contentDescription = "Delete Icon",
                modifier = Modifier.clickable {
                    onTodoDeleteItemClicked(todoItem)
                }
            )

        }
    }
}
