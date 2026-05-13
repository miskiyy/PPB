package com.example.marketsiswa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.marketsiswa.ui.MarketSiswaApp
import com.example.marketsiswa.ui.theme.MarketSiswaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarketSiswaTheme {
                MarketSiswaApp()
            }
        }
    }
}
