package com.mike.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember

internal fun lightColors(isLight: Boolean) = BaupapColors(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    error = md_theme_light_error,
    onError = md_theme_light_onError,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    isLight = isLight,
    primaryVariant = md_theme_light_primaryContainer,
    secondaryVariant = md_theme_light_secondaryContainer
)

internal fun darkColors(isLight: Boolean) = BaupapColors(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryVariant = md_theme_dark_primaryContainer,
    secondaryVariant = md_theme_dark_secondaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    error = md_theme_dark_error,
    onError = md_theme_dark_onError,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    isLight = isLight
)


@Composable
fun BaubapTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        lightColors(true)
    } else {
        darkColors(false)
    }
    ProvideBaubapResources(colors, baubapTypography, baubapShapes) {
        MaterialTheme(
            colors = debugColors(useDarkTheme, colors),
            content = content,
            typography = mdTypography,
            shapes = shapes
        )
    }

}

@Composable
internal fun ProvideBaubapResources(
    colors: BaupapColors,
    typography: BaubapTypography,
    shapes: BaubapShapes,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    CompositionLocalProvider(
        LocalBaubapColors provides colorPalette,
        LocalBaubapTypographies provides typography,
        LocalBaubapShapes provides shapes
    ) {
        ProvideTextStyle(value = typography.text1, content = content)
    }
}


object BaubapTheme {
    val colors: BaupapColors
        @Composable
        get() = LocalBaubapColors.current

    val typography: BaubapTypography
        @Composable
        get() = LocalBaubapTypographies.current

    val shapes: BaubapShapes
        @Composable
        get() = LocalBaubapShapes.current
}