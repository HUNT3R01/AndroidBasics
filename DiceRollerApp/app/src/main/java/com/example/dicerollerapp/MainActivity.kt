package com.example.lab2androidbasics.dicerollerapp

import androidx.compose.ui.text.font.FontWeight
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.dicerollerapp.ui.theme.DiceRollerAppTheme
import androidx.compose.runtime.*
import androidx.compose.material3.Button


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                        Greeting(
                            name = "Creación de Personaje",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                        )
                        CharacterCreatorScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "$name ",
        modifier = modifier
    )
}


@Composable
fun CharacterCreatorScreen() {
    var vit by remember { mutableIntStateOf(10) }
    var dex by remember { mutableIntStateOf(10) }
    var wis by remember { mutableIntStateOf(10) }

    val total = vit + dex + wis

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        StatRow(name = "Vitalidad", value = vit, onRoll = { vit = (0..20).random() })
        StatRow(name = "Destreza", value = dex, onRoll = { dex = (0..20).random() })
        StatRow(name = "Sabiduria", value = wis, onRoll = { wis = (0..20).random() })

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Puntuacion Total: $total", style = MaterialTheme.typography.headlineSmall)

        if(total < 30){
            Text("Recomiendo volver a tirar", color = Color.Red, fontWeight = FontWeight.Bold)
        }else if(total >= 50) {
            Text("Divino!", color = Color(0xFFFFD700), fontWeight = FontWeight.ExtraBold)
        }else{

        }
    }
}

@Composable
fun StatRow(name: String, value: Int, onRoll: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = name, style = MaterialTheme.typography.titleMedium)
            Text(text = value.toString(), style = MaterialTheme.typography.bodyLarge)
            Button(onClick = onRoll) {
                Text("Lanzar")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiceRollerAppTheme {
        Greeting("Android")
        CharacterCreatorScreen()
    }
}
