package com.meeweel.materialdesign.ui.todolist.todorepository

import com.meeweel.materialdesign.ui.todolist.Quest

interface ToDoLocalRepository {
    fun getAllHistory(): MutableList<Quest>
    fun saveEntity(quest: Quest)
    fun deleteAll()
    fun getMyLocalQuestList() : MutableList<Quest>
    fun getDeletedLocalQuestList() : MutableList<Quest>
    fun getDoneLocalQuestList() : MutableList<Quest>
    fun toTrash(quest: Quest)
    fun toDone(quest: Quest)
    fun toMain(quest: Quest)
    fun delete(quest: Quest)
}