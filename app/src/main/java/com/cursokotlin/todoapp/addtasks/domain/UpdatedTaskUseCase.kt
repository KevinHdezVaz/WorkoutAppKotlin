package com.cursokotlin.todoapp.addtasks.domain

import com.cursokotlin.todoapp.addtasks.data.TaskRepository
import com.cursokotlin.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdatedTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
){

    suspend operator fun invoke(taskModel: TaskModel){
        taskRepository.update(taskModel = taskModel)
    }
}