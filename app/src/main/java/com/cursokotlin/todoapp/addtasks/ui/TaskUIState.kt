package com.cursokotlin.todoapp.addtasks.ui

import com.cursokotlin.todoapp.addtasks.ui.model.TaskModel


sealed interface TaskUIState {
    object Loading:TaskUIState
    data class Error (val throwable: Throwable):TaskUIState
    data class Success(val tasks:List<TaskModel>):TaskUIState


}