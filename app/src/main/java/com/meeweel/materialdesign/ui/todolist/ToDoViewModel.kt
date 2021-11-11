package com.meeweel.materialdesign.ui.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.materialdesign.room.App.Companion.getToDoDao
import com.meeweel.materialdesign.ui.todolist.todorepository.ToDoLocalRepository
import com.meeweel.materialdesign.ui.todolist.todorepository.ToDoLocalRepositoryImpl
import com.meeweel.materialdesign.ui.todolist.todorepository.changingQuest
import java.lang.Thread.sleep

class ToDoViewModel(private val repository: ToDoLocalRepository = ToDoLocalRepositoryImpl(getToDoDao())) :
    ViewModel() {
    private val liveDataToObserve: MutableLiveData<ToDoAppState> = MutableLiveData()
    fun getData(): LiveData<ToDoAppState> {
        return liveDataToObserve
    }
    fun getQuestFromLocalSource() {
        sync()
        return getDataFromLocalSource()
    }
    private fun getDataFromLocalSource() {
        liveDataToObserve.value = ToDoAppState.Loading
        Thread {
            sleep(100)
            liveDataToObserve.postValue(
                ToDoAppState.Success(
                    repository.getMyLocalQuestList()
                )
            )
        }.start()
    }
    fun saveChanges(quest: Quest, newQuest: Quest) {
        repository.delete(quest)
        repository.saveEntity(newQuest)
    }
    fun saveNewQuest(quest: Quest) {
        repository.saveEntity(quest)
    }
    fun toDelete(quest: Quest) {
        repository.toTrash(quest)
    }
    fun toMain(quest: Quest) {
        repository.toMain(quest)
    }
    fun toDone(quest: Quest) {
        repository.toDone(quest)
    }
    fun delete(quest: Quest) {
        repository.delete(quest)
    }
    fun sync() {
        for (item in changingQuest) {
            when(item.list) {
                0 -> delete(item)
                1 -> toMain(item)
                2 -> toDone(item)
                3 -> toDelete(item)
            }
        }
        changingQuest = mutableListOf()
    }
}