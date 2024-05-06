package com.cursokotlin.todoapp.addtasks.data.repository

import android.util.Log
import com.cursokotlin.todoapp.addtasks.data.model.Exercise
import com.cursokotlin.todoapp.addtasks.data.remote.ApiService
import javax.inject.Inject

class ExerciseRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun fetchBackExercises(): List<Exercise>? {
        val response = apiService.getBackExercises()
        if (response.isSuccessful) {
            return response.body()
        } else {
            Log.e("ExerciseRepository", "Error fetching exercises: ${response.errorBody()?.string()}")
            return null
        }
    }
}