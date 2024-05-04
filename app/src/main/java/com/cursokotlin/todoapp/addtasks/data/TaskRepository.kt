package com.cursokotlin.todoapp.addtasks.data

import com.cursokotlin.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TaskRepository  @Inject constructor(private val taskDAO: TaskDAO){

    val tasks: Flow<List<TaskModel>> = taskDAO.getTask().map { items-> items.map { TaskModel(it.id,it.task,it.selected) } }


    suspend fun add(taskModel: TaskModel){
        taskDAO.insertTask(TaskEntity(taskModel.id, taskModel.task,taskModel.selected))
    }

    suspend fun update(taskModel: TaskModel){
        taskDAO.updateTask(TaskEntity(taskModel.id, taskModel.task,taskModel.selected))
    }

    suspend fun remove(taskModel: TaskModel){
        taskDAO.removeTask(TaskEntity(taskModel.id, taskModel.task,taskModel.selected))
    }
}