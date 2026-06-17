package com.example.coffebliss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.coffebliss.ui.navigation.CoffeeBlissNavHost
import com.example.coffebliss.ui.theme.CoffeBlissTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoffeBlissTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CoffeeBlissNavHost()
                }
            }
        }
    }
}
