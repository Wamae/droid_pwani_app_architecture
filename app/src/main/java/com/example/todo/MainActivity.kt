package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo.database.TodoApplication
import com.example.todo.ui.theme.TodoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Greeting("Android")
//                     TodoScreen((application as TodoApplication).todoViewModel)
                    NavigationView((application as TodoApplication))
                }
            }
        }
    }
}

@Composable
fun NavigationView(todoApplication: TodoApplication) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            TodoScreen(navController, todoApplication.todoViewModel)
        }
        composable("add_todo_item") {
            AddTodoItemScreen(
                todoApplication.todoViewModel
            ) { navController.popBackStack() }
        }
        composable("edit_todo_item/{todoId}") { navBackStackEntry ->
            val todoId = navBackStackEntry.arguments?.getString("todoId")?.toInt()
            todoId?.let {
                EditTodoItemScreen(
                    it,
                    todoApplication.todoViewModel
                ) { navController.popBackStack() }
            }

        }
    }
}