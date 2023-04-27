package com.example.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoItem::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private var instance: TodoDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): TodoDatabase {
            if (instance == null) instance = Room.databaseBuilder(
                ctx.applicationContext, TodoDatabase::class.java, "todo_database"
            ).fallbackToDestructiveMigration().build()

            return instance!!

        }
    }
}
