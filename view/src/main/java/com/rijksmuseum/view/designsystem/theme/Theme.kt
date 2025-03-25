package com.rijksmuseum.view.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun RijksmuseumTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    typography: Typography = Typography,
    shapes: Shapes = Shapes,
    spacing: Spacing = DefaultSpacing(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }

    CompositionLocalProvider(
        LocalColorScheme provides colors,
        LocalTypography provides typography,
        LocalShapes provides shapes,
        LocalSpacing provides spacing,
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

private val LightColors = lightColors(
    primary = md_theme_light_primary,
    primaryVariant = md_theme_light_primary_variant,
    onPrimary = md_theme_light_onPrimary,
    secondary = md_theme_light_secondary,
    secondaryVariant = md_theme_light_secondary_variant,
    onSecondary = md_theme_light_onSecondary,
    error = md_theme_light_error,
    onError = md_theme_light_onError,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
)

private val DarkColors = darkColors(
    primary = md_theme_dark_primary,
    primaryVariant = md_theme_dark_primary_variant,
    onPrimary = md_theme_dark_onPrimary,
    secondary = md_theme_dark_secondary,
    secondaryVariant = md_theme_dark_secondary_variant,
    onSecondary = md_theme_dark_onSecondary,
    error = md_theme_dark_error,
    onError = md_theme_dark_onError,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
)

object RijksmuseumTheme {

    val colorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val spacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current

}

internal val LocalColorScheme = staticCompositionLocalOf<Colors> {
    error("CompositionLocal LocalColorScheme not present")
}

internal val LocalTypography = staticCompositionLocalOf<Typography> {
    error("CompositionLocal LocalColorScheme not present")
}

internal val LocalShapes = staticCompositionLocalOf<Shapes> {
    error("CompositionLocal LocalColorScheme not present")
}

internal val LocalSpacing = staticCompositionLocalOf<Spacing> {
    error("CompositionLocal LocalColorScheme not present")
}
