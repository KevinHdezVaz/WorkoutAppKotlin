package com.cursokotlin.todoapp

 import ScreenGenders
 import android.content.Context
 import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.platform.LocalContext
 import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.cursokotlin.todoapp.addtasks.ui.TasksScreen
import com.cursokotlin.todoapp.addtasks.ui.TasksViewModel
 import com.cursokotlin.todoapp.ui.theme.TodoAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                val systemUiController = rememberSystemUiController()
                val statusBarColor = Color(0xFF050217)

                SideEffect {
                    systemUiController.setStatusBarColor(statusBarColor)
                }
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "first") {
                    composable("first") { TasksScreen(navController, LocalContext.current) }
                    composable("second") { ScreenGenders(navController) }
                }
            }
        }
    }

}
