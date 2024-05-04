package com.cursokotlin.todoapp.addtasks.ui

 import VideoPlayerComposable
 import android.content.Context
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.cursokotlin.todoapp.R


@Composable
fun TasksScreen(navController: NavController, context : Context) {

    Box(modifier = Modifier.fillMaxSize()) {

        VideoPlayerComposable(

            videoUri = Uri.parse("android.resource://${context.packageName}/${R.raw.vidiox}"))

        InfoCentral()

        val animationSpec = rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.estawera)
        )

        val composition by animationSpec
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever
        )

        LottieAnimation(

            composition = composition,
            progress = progress,
            modifier = Modifier.size(200.dp).align(Alignment.TopCenter)

        )
        Button(
            modifier =
            Modifier.align(alignment = Alignment.BottomCenter).padding(bottom = 30.dp),
            onClick = { navController.navigate("second") }) {

            Text(text = "Empezar", color = Color.White, fontWeight = FontWeight.Bold)
        }

    }

}



@Composable
fun InfoCentral() {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 180.dp, bottom = 50.dp) // Ajusta el margen vertical aquí

    ) {


        Surface(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 50.dp),
            shape = RoundedCornerShape(34.dp),


            ) {


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Hola! Bienvenido a tu entrenador personal con IA", // Convertir a String si es necesario
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )



                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Te hare algunas preguntas para personalizar un plan de entrenamiento para ti", // Convertir a String si es necesario
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )




                Divider(

                    modifier =
                    Modifier.padding(top = 18.dp, start = 8.dp, end = 8.dp, bottom = 16.dp),
                    thickness = 1.dp,
                    color = Color(0xFFFF5722)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DogDateColumn(
                        modifier = Modifier.weight(1f),
                        "Enfocate en ti ", "Suma cada dia", "asdfa"
                    )
                    VerticalDivider()
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = "LOGO DE APP",
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,

                            )


                    }

                    VerticalDivider()

                    DogDateColumn(
                        modifier = Modifier.weight(1f),
                        "MALE",
                        "FEMALE", "asdf"

                    )

                }

            }
        }
    }
}

@Composable
private fun VerticalDivider() {
    Divider(
        modifier = Modifier
            .height(42.dp)
            .width(1.dp), color = Color.White
    )
}

@Composable
private fun DogDateColumn(modifier: Modifier, genre: String, weight: String, height: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Text(
            modifier = Modifier.padding(top = 18.dp),
            text = genre,
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,

            )

        Text(
            modifier = Modifier.padding(top = 18.dp, bottom = 18.dp),
            text = weight,
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,

            )



        Text(
            modifier = Modifier.padding(top = 18.dp, bottom = 18.dp),
            text = height,
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,

            )


    }
}