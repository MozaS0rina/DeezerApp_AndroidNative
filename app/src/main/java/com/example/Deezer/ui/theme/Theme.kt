package com.example.Deezer.ui.theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = PrimaryDark,
    primaryVariant = PrimaryVariantDark,
    secondary = SecondaryDark,
    background = BackgroundDark,
    surface = SurfaceDark,
    onPrimary = OnPrimaryDark,
    onSecondary = OnSecondaryDark,
    onBackground = OnBackgroundDark,
    onSurface = OnSurfaceDark
)

private val LightColorPalette = lightColors(
    primary = PrimaryLight,
    primaryVariant = PrimaryVariantLight,
    secondary = SecondaryLight,
    background = BackgroundLight,
    surface = SurfaceLight,
    onPrimary = OnPrimaryLight,
    onSecondary = OnSecondaryLight,
    onBackground = OnBackgroundLight,
    onSurface = OnSurfaceLight
)

@Composable
fun DeezerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}