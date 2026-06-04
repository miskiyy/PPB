package com.example.newsapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PurplePrimary,
    secondary = PinkSecondary,
    tertiary = PinkLightContainer,
    background = androidx.compose.ui.graphics.Color(0xFF190F1C),
    surface = androidx.compose.ui.graphics.Color(0xFF190F1C),
    surfaceVariant = androidx.compose.ui.graphics.Color(0xFF2A1C30),
    onPrimary = androidx.compose.ui.graphics.Color.White,
    onSecondary = androidx.compose.ui.graphics.Color.White,
    onBackground = androidx.compose.ui.graphics.Color(0xFFF1E3F4),
    onSurface = androidx.compose.ui.graphics.Color(0xFFF1E3F4)
)

private val LightColorScheme = lightColorScheme(
    primary = PurplePrimary,
    secondary = PinkSecondary,
    tertiary = PinkDark,
    background = PinkPurpleBackground,
    surface = PinkPurpleSurface,
    surfaceVariant = PinkPurpleSurfaceVariant,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    onSecondary = androidx.compose.ui.graphics.Color.White,
    primaryContainer = PurpleLightContainer,
    secondaryContainer = PinkLightContainer,
    onPrimaryContainer = PurpleDark,
    onSecondaryContainer = PinkDark
)

@Composable
fun NewsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Set default dynamicColor to false to keep our gorgeous pink/purple theme active
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}