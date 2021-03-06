package com.meeweel.materialdesign.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ToDoEntity::class], version = 1, exportSchema = false)
abstract class ToDoDataBase : RoomDatabase() {
    abstract fun historyDao(): ToDoDao
}