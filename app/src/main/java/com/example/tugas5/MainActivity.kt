package com.example.tugas5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
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
                CalculatorScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
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
                    text = "Kalkulator",
                    fontFamily = interBold,
                    fontSize = 28.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                TextField(
                    value = num1,
                    onValueChange = { num1 = it },
                    label = { Text("Angka 1", fontFamily = interSemiBold) },
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = num2,
                    onValueChange = { num2 = it },
                    label = { Text("Angka 2", fontFamily = interSemiBold) },
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = {
                        result = performOperation(num1, num2, "+") }) {
                        Text("Add", fontFamily = interSemiBold)
                    }
                    Button(onClick = {
                        result = performOperation(num1, num2, "-") }) {
                        Text("Sub", fontFamily = interSemiBold)
                    }
                    Button(onClick = {
                        result = performOperation(num1, num2, "*") }) {
                        Text("Mul", fontFamily = interSemiBold)
                    }
                    Button(onClick = {
                        result = performOperation(num1, num2, "/") }) {
                        Text("Div", fontFamily = interSemiBold)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Hasil:",
                    fontFamily = interBold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                result?.let {
                    Text(
                        text = it,
                        fontFamily = interSemiBold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

fun performOperation(num1: String, num2: String, operator: String): String {
    return try {
        val n1 = num1.toInt()
        val n2 = num2.toInt()
        val res = when (operator) {
            "+" -> n1 + n2
            "-" -> n1 - n2
            "*" -> n1 * n2
            "/" -> if (n2 != 0) n1 / n2 else "Error: Pembagian dengan nol!"
            else -> "Operator tidak valid!"
        }
        res.toString()
    } catch (e: NumberFormatException) {
        "Error: Masukkan angka yang valid!"
    }
}