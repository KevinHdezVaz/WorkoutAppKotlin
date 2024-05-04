package com.cursokotlin.todoapp.addtasks.ui

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursokotlin.todoapp.addtasks.domain.AddTaskUseCase
import com.cursokotlin.todoapp.addtasks.domain.DeleteTaskUseCase
import com.cursokotlin.todoapp.addtasks.domain.GetTasksUseCase
import com.cursokotlin.todoapp.addtasks.domain.UpdatedTaskUseCase
import com.cursokotlin.todoapp.addtasks.ui.TaskUIState.*
import com.cursokotlin.todoapp.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updatedTaskUseCase: UpdatedTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase   ,

    getTasksUseCase: GetTasksUseCase
): ViewModel() {

    val uiState:StateFlow<TaskUIState> = getTasksUseCase().map ( ::Success )
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)


    private val _showdialog = MutableLiveData<Boolean>()
    val showdialog: LiveData<Boolean> = _showdialog


// private val _tasks = mutableStateListOf<TaskModel>()
 //val task: List<TaskModel> = _tasks

    fun onDialogClose() {
        _showdialog.value = false
    }

    fun onTaskCreate(task: String) {
        _showdialog.value = false
         viewModelScope.launch {
            addTaskUseCase(TaskModel(task = task))
        }
    }

    fun onshowDialog() {
        _showdialog.value = true
    }

    fun onitemRemove(taskModel: TaskModel) {

        /*    val task = _tasks.find { it.id == taskModel.id}

            _tasks.remove(task)
        */

        viewModelScope.launch {
            deleteTaskUseCase(taskModel.copy(selected = !taskModel.selected))
        }

    }
    fun oncheckboxSelected(taskModel: TaskModel) {
/*
        val index = _tasks.indexOf(taskModel)
        _tasks[index] = _tasks[index].let {
            it.copy(selected = !it.selected)

 */

        viewModelScope.launch {
            updatedTaskUseCase(taskModel.copy(selected = !taskModel.selected))
        }


        }
    }








