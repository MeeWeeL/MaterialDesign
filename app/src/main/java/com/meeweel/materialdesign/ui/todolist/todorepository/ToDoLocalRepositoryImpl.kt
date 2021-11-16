package com.meeweel.materialdesign.ui.todolist.todorepository

import com.meeweel.materialdesign.room.ToDoDao
import com.meeweel.materialdesign.room.convertQuestToEntity
import com.meeweel.materialdesign.room.convertToDoEntityToQuest
import com.meeweel.materialdesign.ui.todolist.Quest

class ToDoLocalRepositoryImpl(private val localDataSource: ToDoDao) : ToDoLocalRepository {
    override fun getAllHistory(): MutableList<Quest> {
        return convertToDoEntityToQuest(localDataSource.all())
    }

    override fun saveEntity(quest: Quest) {
        return localDataSource.insert(convertQuestToEntity(quest, 1))
    }

    override fun deleteAll() {
        localDataSource.deleteAll()
    }
    override fun getMyLocalQuestList() : MutableList<Quest> {
        val localMyQuestList: MutableList<Quest> = mutableListOf()
        for (item in convertToDoEntityToQuest(localDataSource.all().reversed())) {
            if (item.list == 1) localMyQuestList.add(item)
        }
        return localMyQuestList
    }
    override fun getDeletedLocalQuestList() : MutableList<Quest> {
        val localMyQuestList: MutableList<Quest> = mutableListOf()
        for (item in convertToDoEntityToQuest(localDataSource.all().reversed())) {
            if (item.list == 3) localMyQuestList.add(item)
        }
        return localMyQuestList
    }
    override fun getDoneLocalQuestList() : MutableList<Quest> {
        val localMyQuestList: MutableList<Quest> = mutableListOf()
        for (item in convertToDoEntityToQuest(localDataSource.all().reversed())) {
            if (item.list == 2) localMyQuestList.add(item)
        }
        return localMyQuestList
    }
    override fun toTrash(quest: Quest) {
        localDataSource.delete(quest.title)
        localDataSource.insert(convertQuestToEntity(quest, 3))
    }
    override fun toDone(quest: Quest) {
        localDataSource.delete(quest.title)
        localDataSource.insert(convertQuestToEntity(quest, 2))
    }
    override fun toMain(quest: Quest) {
        localDataSource.delete(quest.title)
        localDataSource.insert(convertQuestToEntity(quest, 1))
    }
    override fun delete(quest: Quest) {
        localDataSource.delete(quest.title)
    }
}