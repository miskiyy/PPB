package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                // Kontainer permukaan menggunakan warna latar belakang dari tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Memanggil fungsi GreetingText di dalam aktivitas utama
                    GreetingText(
                        message = "Happy Birthday Sam!",
                        from = "From Emma"
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    // Menggunakan Column agar teks tidak tumpang tindih (tersusun vertikal)
    Column(modifier = modifier) {
        Text(
            text = message,
            fontSize = 100.sp,     // Mengatur ukuran font pesan
            lineHeight = 116.sp    // Mengatur jarak antar baris
        )
        Text(
            text = from,
            fontSize = 36.sp       // Mengatur ukuran font pengirim
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        // Tampilan pratinjau di Android Studio
        GreetingText(
            message = "Happy Birthday Sam!",
            from = "From Emma"
        )
    }
}