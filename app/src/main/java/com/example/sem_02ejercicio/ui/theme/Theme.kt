package com.example.sem_02ejercicio.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFF1976D2),
    secondary = androidx.compose.ui.graphics.Color(0xFF388E3C)
)

@Composable
fun ActividadTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}
