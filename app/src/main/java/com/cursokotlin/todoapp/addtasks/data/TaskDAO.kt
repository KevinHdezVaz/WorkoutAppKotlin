package com.cursokotlin.todoapp.addtasks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {

    @Query("SELECT * from TaskDataBase")
    fun getTask(): Flow<List<TaskEntity>>

    @Insert
    suspend fun insertTask(item:TaskEntity)


    @Update
    suspend fun updateTask(item:TaskEntity)


    @Delete
    suspend fun removeTask(item:TaskEntity)

}