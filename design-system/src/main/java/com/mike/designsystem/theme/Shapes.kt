package com.mike.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

private val radius100 = 4.dp
private val radius200 = 8.dp
private val radius300 = 12.dp
private val radius400 = 16.dp
private val radius500 = 20.dp
private val radius600 = 24.dp
private val radius700 = 32.dp
private val radius800 = 48.dp

val baubapShapes = BaubapShapes(
    radius100 = RoundedCornerShape(radius100),
    radius200 = RoundedCornerShape(radius200),
    radius300 = RoundedCornerShape(radius300),
    radius400 = RoundedCornerShape(radius400),
    radius500 = RoundedCornerShape(radius500),
    radius600 = RoundedCornerShape(radius600),
    radius700 = RoundedCornerShape(radius700),
    radius800 = RoundedCornerShape(radius800)
)

@Immutable
data class BaubapShapes(
    val radius100: RoundedCornerShape,
    val radius200: RoundedCornerShape,
    val radius300: RoundedCornerShape,
    val radius400: RoundedCornerShape,
    val radius500: RoundedCornerShape,
    val radius600: RoundedCornerShape,
    val radius700: RoundedCornerShape,
    val radius800: RoundedCornerShape
)

internal val LocalBaubapShapes = compositionLocalOf<BaubapShapes> {
    error("No Shapes provided")
}

val shapes = Shapes(
    small = baubapShapes.radius100,
    medium = baubapShapes.radius100,
    large = RoundedCornerShape(0.dp)
)

