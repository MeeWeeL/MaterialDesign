package com.meeweel.materialdesign.ui.todolist

sealed class ToDoAppState {
    data class Success(val questData: MutableList<Quest>) : ToDoAppState()
    class Error(val error: Throwable) : ToDoAppState()
    object Loading : ToDoAppState()
}