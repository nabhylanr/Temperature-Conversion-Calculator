package com.example.tugas5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tugas5.ui.theme.Tugas5Theme

val interBold = FontFamily(Font(R.font.inter_bold))
val interSemiBold = FontFamily(Font(R.font.inter_semibold))

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tugas5Theme {
                TemperatureConverterScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemperatureConverterScreen() {
    var celcius by remember { mutableStateOf("")}
    var result by remember { mutableStateOf<String?>(null) }

    Scaffold { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = "Konversi Suhu",
                    fontFamily = interBold,
                    fontSize = 28.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                TextField(
                    value = celcius,
                    onValueChange = { celcius = it },
                    label = { Text("Masukkan suhu dalam Celcius", fontFamily = interSemiBold,)  },
                    textStyle = TextStyle(fontFamily = interSemiBold),
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = {
                        result = convertTemperature(celcius, "F")
                    }) {
                        Text("Fahrenheit", fontFamily = interSemiBold,)
                    }
                    Button(onClick = {
                        result = convertTemperature(celcius, "K")
                    }) {
                        Text("Kelvin", fontFamily = interSemiBold,)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Hasil:",
                    fontSize = 20.sp,
                    fontFamily = interBold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                result?.let {
                    Text(
                        text = it,
                        fontSize = 18.sp,
                        fontFamily = interSemiBold,
                    )
                }
            }
        }
    }
}

fun convertTemperature(celcius: String, toUnit: String): String {
    return try {
        val c = celcius.toDouble()
        val converted = when (toUnit) {
            "F" -> c * 9/5 + 32
            "K" -> c + 273.15
            else -> "Unit tidak valid"
        }
        "%.2f $toUnit".format(converted)
    } catch (e: NumberFormatException) {
        "Error: Masukkan angka yang valid!"
    }
}