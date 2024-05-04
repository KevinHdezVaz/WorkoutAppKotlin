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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cursokotlin.todoapp.R

@Composable
fun ScreenGenders(navController: NavController) {
    var selectedGender by remember { mutableStateOf<String?>(null) }
    Box(
        modifier = Modifier
            .background(Color(0xFF050217))
            .padding(start = 8.dp, end = 8.dp, bottom = 16.dp),
        contentAlignment = Alignment.TopCenter
    ){


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
                drawableId = R.drawable.ic_launcher_background,
                isSelected = selectedGender == "Masculino",
                onSelect = { selectedGender = it }
            )
            GenderCard(
                gender = "Femenino",
                drawableId = R.drawable.ic_launcher_background,
                isSelected = selectedGender == "Femenino",
                onSelect = { selectedGender = it }
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            if (selectedGender != null) {
                // Puedes cambiar "destinationScreen" por el nombre real de la ruta de destino
                navController.navigate("destinationScreen/${selectedGender}")
            }
        }) {
            Text("Continuar", color = Color.White)
        }
    }
    }
}

@Composable
fun GenderCard(gender: String, drawableId: Int, isSelected: Boolean, onSelect: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
            .height(250.dp)
            .clickable { onSelect(gender) },
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
                contentDescription = gender
            )
            Text(text = gender, fontWeight = FontWeight.Medium)
        }
    }
}
