package com.example.todo.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var description: String,
    val completed: Boolean = false,
    val createdTime: Long = System.currentTimeMillis()
)
