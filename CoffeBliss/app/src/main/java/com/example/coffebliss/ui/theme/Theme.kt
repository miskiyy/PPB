package com.example.coffebliss.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CoffeeColorScheme = lightColorScheme(
    primary = CoffeeGreenDark,
    onPrimary = Color.White,
    primaryContainer = CoffeeGreen,
    onPrimaryContainer = CoffeeOnGreen,
    secondary = CoffeeBrown,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFDCC2),
    onSecondaryContainer = Color(0xFF2A1500),
    tertiary = CoffeeGold,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFE0B2),
    onTertiaryContainer = Color(0xFF2E1500),
    background = CoffeeCream,
    onBackground = CoffeeTextDark,
    surface = CoffeeWhite,
    onSurface = CoffeeTextDark,
    surfaceVariant = Color(0xFFEDE0D4),
    onSurfaceVariant = Color(0xFF4E4539),
    outline = Color(0xFF80746A),
    error = CoffeeError,
    onError = Color.White
)

@Composable
fun CoffeBlissTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CoffeeColorScheme,
        typography = Typography,
        content = content
    )
}
