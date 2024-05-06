package com.cursokotlin.todoapp.addtasks.ui.inicio


    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.items
    import androidx.compose.material.CircularProgressIndicator
 import androidx.compose.material.Text
 import androidx.compose.runtime.Composable
 import androidx.compose.runtime.collectAsState
 import androidx.compose.runtime.getValue
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.text.style.TextAlign
    import com.cursokotlin.todoapp.addtasks.ui.viewmodel.ExerciseViewModel
 import androidx.hilt.navigation.compose.hiltViewModel
    import com.cursokotlin.todoapp.addtasks.data.model.Exercise


@Composable
fun ExerciseListScreen(viewModel: ExerciseViewModel = hiltViewModel()) {
    val exercises by viewModel.exercises.collectAsState()

    when {
        exercises == null -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        exercises!!.isEmpty() -> {
            Text("No data found", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        }
        else -> {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(exercises!!) { exercise ->
                    ExerciseItem(exercise)
                }
            }
        }
    }
}

@Composable
fun ExerciseItem(exercise: Exercise) {
    Text(text = "Name: ${exercise.name}",color= Color.White,  modifier = Modifier.fillMaxWidth())
    Text(text = "Target: ${exercise.target}", color= Color.White,modifier = Modifier.fillMaxWidth())
}