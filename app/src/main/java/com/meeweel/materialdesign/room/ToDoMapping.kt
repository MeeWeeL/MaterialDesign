package com.meeweel.materialdesign.room

import com.meeweel.materialdesign.ui.todolist.Quest

fun convertToDoEntityToQuest(entityList: List<ToDoEntity>): MutableList<Quest> {
    val a = entityList.map {
        Quest(it.list, it.title, it.description, it.image, it.imageInt)
    }
    return a.toMutableList()
}

fun convertQuestToEntity(quest: Quest, list: Int): ToDoEntity {
    return ToDoEntity(0, list, quest.title,quest.description,image = quest.image,imageInt = quest.imageInt)
}