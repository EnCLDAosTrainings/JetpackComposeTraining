package com.example.netflicks.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightNetflicksColors = lightColors(
    primary = WhiteApplication,
    onPrimary = BlackApplication,
    secondary = BlackApplication,
    onSecondary = WhiteApplication,
    secondaryVariant = GoldApplication,
)

private val DarkNetflicksColors = darkColors(
    primary = BlueApplication,
    onPrimary = WhiteApplication,
    secondary = GoldApplication,
    onSecondary = BlueApplication,
    secondaryVariant = WhiteApplication
//    primary = BlackApplication,
//    surface = BlueApplication,
//    onSurface = WhiteApplication,
//    background = GoldApplication,
//    onBackground = BlueApplication,
)

@Composable
fun NetflicksTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkNetflicksColors
    } else {
        LightNetflicksColors
    }
    MaterialTheme(
        colors = colors,
        typography = Typo,
        shapes = Shapes,
        content = content
    )
}