package com.example.hansotbob.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    secondary = WarmHomeColor,
    background = Color.White,
    surface = Color.White,
    error = Color.Red,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

val LocalExtraColors = staticCompositionLocalOf {
    ExtraColors(
        share = ShareColor,
        share2 = ShareColor2,
        accent1 = AccentColor1,
        accent2 = AccentColor2
    )
}

data class ExtraColors(
    val share: Color,
    val share2: Color,
    val accent1: Color,
    val accent2: Color
)

@Composable
fun HansotbobTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalExtraColors provides ExtraColors(
        share = ShareColor,
        share2 = ShareColor2,
        accent1 = AccentColor1,
        accent2 = AccentColor2
    )) {
        MaterialTheme(
            colorScheme = LightColorScheme,
            typography = Typography,
            shapes = Shapes,
            content = content,
        )
    }
}