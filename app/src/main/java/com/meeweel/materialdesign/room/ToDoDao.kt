package com.meeweel.materialdesign.room

import androidx.room.*

@Dao
interface ToDoDao {
    @Query("SELECT * FROM ToDoEntity")
    fun all(): MutableList<ToDoEntity>

    @Query("SELECT * FROM ToDoEntity WHERE title LIKE :title")
    fun getDataByWord(title: String): List<ToDoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: ToDoEntity)

    @Update
    fun update(entity: ToDoEntity)

    @Query("DELETE FROM ToDoEntity")
    fun deleteAll()

    @Query("DELETE FROM ToDoEntity WHERE title LIKE :title")
    fun delete(title: String)
}