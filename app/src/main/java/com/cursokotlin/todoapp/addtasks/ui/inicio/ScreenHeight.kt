package com.cursokotlin.todoapp.addtasks.ui.inicio

import android.graphics.fonts.Font
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.commandiron.wheel_picker_compose.WheelDateTimePicker
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.cursokotlin.todoapp.R
import com.cursokotlin.todoapp.addtasks.util.VibrationButton
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import kotlin.math.pow
import kotlin.math.roundToInt

@Composable
fun ScreenHeight(navController: NavController,gender: String) {

    var weight by remember { mutableStateOf(0f) }  // Estado para almacenar el peso
    var height by remember { mutableStateOf(0f) }  // Estado para almacenar la altura
    var age by remember { mutableStateOf(0f) }  // Estado para almacenar la altura

    val context = LocalContext.current
    // Calcular el IMC
    val bmi = calculateBMI(weight, height)
    val bmiCategory = getBMICategory(bmi)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                backgroundColor = Color(0xFF050217),
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),  // Replace with your actual resource ID
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        backgroundColor = Color(0xFF050217)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(Color(0xFF050217))

        ) {
            TextoArriba()


            SliderParameterCard("Tu peso en, KG", 40f, 150f, "Kg", R.drawable.muscul, weight) { newWeight -> weight = newWeight }
            SliderParameterCard("Tu altura en, CM", 140f, 220f, "cm", R.drawable.ejercicio2, height) { newHeight -> height = newHeight }
            SliderParameterCard("Tu edad, AÑOS", 15f, 70f, "años", R.drawable.ejercicio3,age) { newage ->
                age = newage
            }


            Divider(thickness = 2.dp, color = Color.Gray, modifier = Modifier.padding(top = 20.dp, bottom = 20.dp))


            if (weight > 0 && height > 0) {
                Text(
                    "IMC: ${bmi.format(2)} ($bmiCategory)",
                    color = Color.White,
                    fontSize = 19.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Divider(thickness = 2.dp, color = Color.Gray, modifier = Modifier.padding(top = 20.dp, bottom = 20.dp))


            Button(
                onClick = {
sendDataToAPI(weight, height, age,gender)
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(20.dp)
            ) {
                Text("Continuar", color = Color.White,   fontSize = 18.sp )
            }
        }
    }
}

@Composable
fun TextoArriba() {

    Text(
        text = "Para elegir tu rutina personalizada, rellena los siguientes datos:",
        style = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            color = Color.White

        ),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)  // Asegura que el texto esté centrado en el ancho completo
    )
}



@Composable
fun SliderParameterCard(
    label: String,
    min: Float,
    max: Float,
    unit: String,
    backgroundImage: Int,
    value: Float,
    onValueChange: (Float) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(18.dp)
            .fillMaxWidth()
            .height(140.dp),
    ) {
        Box {
            Image(
                painter = painterResource(id = backgroundImage),
                contentDescription = label,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer { alpha = 0.2f },
            )

            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            ) {
                Text(label, style = MaterialTheme.typography.subtitle1, color = Color.White)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {
                        if (value > min) onValueChange(value - 1)
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Decrease",
                            tint = Color.White
                        )
                    }

                    val contextx = LocalContext.current
                    Slider(
                        value = value,
                        onValueChange = {
                            onValueChange(it.roundToInt().toFloat())
                            VibrationButton(contextx )

                        },
                        valueRange = min..max,
                        steps = (max - min - 1).toInt(),
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(onClick = {
                        if (value < max) onValueChange(value + 1)
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Increase",
                            tint = Color.White
                        )
                    }
                }

                // Mostrar el valor como cm y m si la unidad es "cm"
                if (unit == "cm") {
                    val meters = value / 100
                    Text(
                        "${value.roundToInt()} cm / ${meters.format(2)} mts",
                        style = MaterialTheme.typography.h5,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                } else {
                    Text(
                        "${value.roundToInt()} $unit",
                        style = MaterialTheme.typography.h5,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

fun Float.format(digits: Int) = java.lang.String.format("%.${digits}f", this)



fun sendDataToAPI(weight: Float, height: Float, age: Float, gender: String) {
     println("Datos enviados: Peso: $weight kg, Altura: $height cm, Edad: $age años, Género: $gender")
}




fun calculateBMI(weight: Float, height: Float): Float {
    return if (height > 0) weight / (height / 100).pow(2) else 0f
}

fun getBMICategory(bmi: Float): String {
    return when {
        bmi < 18.5 -> "Bajo peso"
        bmi < 25 -> "Normal"
        bmi < 30 -> "Sobrepeso"
        else -> "Obesidad"
    }
}

