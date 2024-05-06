package com.cursokotlin.todoapp.addtasks.data.remote

import com.cursokotlin.todoapp.addtasks.data.model.Exercise
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("exercises/bodyPart/back?limit=10")
    suspend fun getBackExercises(): Response<List<Exercise>>
}