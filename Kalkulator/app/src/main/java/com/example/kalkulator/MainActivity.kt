package com.example.kalkulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
//import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kalkulator.ui.theme.KalkulatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KalkulatorTheme {
                // Surface sebagai container utama mengikuti background tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFFFF0F5) // Soft Pink Background
                ) {
                    CalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    // State untuk menyimpan input user dan hasil
    var firstNumber by remember { mutableStateOf("") }
    var secondNumber by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("Hasil: 0") }

    // Definisi warna (Pinky Palette)
    val hotPink = Color(0xFFFF69B4)
    val deepPink = Color(0xFFDB7093)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Calculator ✨",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = hotPink
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Input Field 1
        NumberInputField(
            value = firstNumber,
            onValueChange = { firstNumber = it },
            label = "Angka Pertama"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input Field 2
        NumberInputField(
            value = secondNumber,
            onValueChange = { secondNumber = it },
            label = "Angka Kedua"
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Baris Tombol Operasi
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val operations = listOf("+", "-", "*", "/")
            operations.forEach { symbol ->
                CalculationButton(
                    symbol = symbol,
                    buttonColor = hotPink,
                    onClick = {
                        resultText = performCalculation(firstNumber, secondNumber, symbol)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Display Hasil
        Text(
            text = resultText,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = deepPink
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFFF69B4),
            unfocusedBorderColor = Color(0xFFDB7093),
            focusedLabelColor = Color(0xFFFF69B4),
            unfocusedLabelColor = Color(0xFFDB7093),
            cursorColor = Color(0xFFFF69B4)
        )
    )
}

@Composable
fun CalculationButton(
    symbol: String,
    buttonColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(64.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        contentPadding = PaddingValues(0.dp) // Agar text di tengah sempurna
    ) {
        val displaySymbol = when(symbol) {
            "*" -> "×"
            "/" -> "÷"
            else -> symbol
        }
        Text(
            text = displaySymbol,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

/**
 * Fungsi murni logika matematika (Pure Logic)
 */
fun performCalculation(n1: String, n2: String, op: String): String {
    val num1 = n1.toDoubleOrNull()
    val num2 = n2.toDoubleOrNull()

    if (num1 == null || num2 == null) return "Isi angka dulu ya! 🎀"

    val result = when (op) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        "*" -> num1 * num2
        "/" -> if (num2 != 0.0) num1 / num2 else return "Gabisa bagi nol! ❌"
        else -> 0.0
    }

    // Format agar jika bulat tidak muncul .0 (misal 5.0 jadi 5)
    val formattedResult = if (result % 1 == 0.0) result.toInt().toString() else result.toString()
    return "Hasil: $formattedResult ✨"
}

// --- Preview biar bisa intip di Android Studio ---
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorPreview() {
    KalkulatorTheme {
        CalculatorScreen()
    }
}