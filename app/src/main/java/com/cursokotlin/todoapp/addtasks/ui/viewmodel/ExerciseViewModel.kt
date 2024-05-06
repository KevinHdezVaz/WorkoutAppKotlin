package com.cursokotlin.todoapp.addtasks.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursokotlin.todoapp.addtasks.data.model.Exercise
import com.cursokotlin.todoapp.addtasks.data.repository.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository

) : ViewModel() {
    private val _exercises = MutableStateFlow<List<Exercise>?>(null)
    val exercises: StateFlow<List<Exercise>?> = _exercises

    init {
        fetchExercises()
    }

    private fun fetchExercises() {
        viewModelScope.launch {
            val fetchedExercises = exerciseRepository.fetchBackExercises()
            _exercises.value = fetchedExercises
            fetchedExercises?.forEach { exercise ->
                Log.e("ExerciseVM", "Exercise: ${exercise.name}, Target: ${exercise.target}")
            }
        }
    }
}