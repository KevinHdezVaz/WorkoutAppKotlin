import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cursokotlin.todoapp.R
import com.cursokotlin.todoapp.addtasks.util.VibrationButton

@Composable
fun ScreenGenders(navController: NavController) {
    var selectedGender by remember { mutableStateOf<String?>(null) }

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
    ){ paddingValues ->

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            color = Color.White,
            text = "¿Cuál es tu género?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            GenderCard(
                gender = "Masculino",
                drawableId = R.drawable.hombrecate,
                isSelected = selectedGender == "Masculino",
                onSelect = { selectedGender = it }
            )
            GenderCard(
                gender = "Femenino",
                drawableId = R.drawable.mujercate,
                isSelected = selectedGender == "Femenino",
                onSelect = { selectedGender = it }
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            if (selectedGender != null) {
                 navController.navigate("tercero/${selectedGender}")

            }
        }) {
            Text("Continuar", color = Color.White,  fontSize = 18.sp  // Aumenta el tamaño de la fuente
            )
        }
    }
    }
}

@Composable
fun GenderCard(gender: String, drawableId: Int, isSelected: Boolean, onSelect: (String) -> Unit) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
            .height(350.dp)
            .clickable { onSelect(gender)
                VibrationButton(context = context)

            },
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        border = if (isSelected) BorderStroke(3.dp, Color(0xFFFF5722)) else null
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = gender,
                contentScale = ContentScale.Crop,
            modifier = Modifier.weight(0.9f).graphicsLayer {  alpha = 0.8f
            }
                )
            Text(text = gender, fontWeight = FontWeight.Medium, modifier = Modifier.weight(0.1f))
        }
    }
}
