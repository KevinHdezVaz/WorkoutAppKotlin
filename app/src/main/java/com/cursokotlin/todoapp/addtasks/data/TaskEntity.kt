package com.cursokotlin.todoapp.addtasks.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TaskDataBase")

data class TaskEntity(
    @PrimaryKey
    val id: Int ,
    var task:String,
     var selected:Boolean = false

)