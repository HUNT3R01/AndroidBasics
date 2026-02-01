package com.example.trafficlightsimulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import com.example.trafficlightsimulator.ui.theme.TrafficLightSimulatorTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrafficLightSimulatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding).fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Greeting(
                            name = "Simulador de Semaforo de Transito",
                            modifier = Modifier.padding(innerPadding)
                        )
                        DelayLightSimulator()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = " $name",
        modifier = modifier
    )
}

@Composable
fun LightCircle(color: Color, isOn: Boolean){
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = if (isOn) color else Color.Gray.copy(alpha = 0.3f),
                shape = CircleShape
            )
            .then(
                if (isOn) Modifier.background(color.copy(alpha = 0.5f), CircleShape) else Modifier
            )
    )
}

@Composable
fun DelayLightSimulator() {
    var state by remember { mutableStateOf(0)}

    LaunchedEffect(Unit) { // Runs ONCE when app starts
        while(true) { // Infinite Loop
            state = 0
            delay(2000)
            state = 1
            delay(1000)
            state = 2
            delay(2000)
        }
    }
    Column(
        modifier = Modifier
            .background(Color.DarkGray, shape = CircleShape)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LightCircle(color = Color.Red, isOn = state == 0)
        LightCircle(color = Color.Yellow, isOn = state == 1)
        LightCircle(color = Color.Green, isOn = state == 2)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TrafficLightSimulatorTheme {
        Greeting("Android")
        DelayLightSimulator()
    }
}