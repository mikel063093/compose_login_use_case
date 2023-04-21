package com.mike.designsystem.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mike.designsystem.R

private val robotoCondensed = FontFamily(
    Font(R.font.roboto_condensed_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.roboto_condensed_regularitalic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.roboto_condensed_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.roboto_condensed_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.roboto_condensed_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.roboto_condensed_lightitalic, FontWeight.Light, FontStyle.Italic),
)

private val roboto = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.roboto_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.roboto_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.roboto_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.roboto_lightitalic, FontWeight.Light, FontStyle.Italic),
)

val baubapTypography = BaubapTypography(
    h1 = TextStyle(
        fontFamily = robotoCondensed,
        fontSize = 42.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 44.sp
    ),
    h2 = TextStyle(
        fontFamily = roboto,
        fontSize = 40.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 44.sp
    ),
    h3 = TextStyle(
        fontFamily = roboto,
        fontSize = 36.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 40.sp
    ),
    h4 = TextStyle(
        fontFamily = roboto,
        fontSize = 28.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 32.sp
    ),
    h5 = TextStyle(
        fontFamily = roboto,
        fontSize = 24.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 28.sp
    ),
    h6 = TextStyle(
        fontFamily = roboto,
        fontSize = 20.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 24.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = roboto,
        fontSize = 16.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 24.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = roboto,
        fontSize = 15.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 20.sp
    ),
    body1 = TextStyle(
        fontFamily = roboto,
        fontSize = 17.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 28.sp
    ),
    body2 = TextStyle(
        fontFamily = roboto,
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 24.sp
    ),
    body3 = TextStyle(
        fontFamily = roboto,
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 20.sp
    ),
    text1 = TextStyle(
        fontFamily = roboto,
        fontSize = 17.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 28.sp
    ),
    text2 = TextStyle(
        fontFamily = roboto,
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 24.sp
    ),
    text3 = TextStyle(
        fontFamily = roboto,
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 20.sp
    ),
    text1Bold = TextStyle(
        fontFamily = roboto,
        fontSize = 17.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 28.sp
    ),
    text2Bold = TextStyle(
        fontFamily = roboto,
        fontSize = 16.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 24.sp
    ),
    text3Bold = TextStyle(
        fontFamily = roboto,
        fontSize = 14.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 20.sp
    ),
    button = TextStyle(
        fontFamily = roboto,
        fontSize = 16.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 16.sp
    ),
    caption = TextStyle(
        fontFamily = roboto,
        fontSize = 12.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 16.sp
    ),
    overline = TextStyle(
        fontFamily = roboto,
        fontSize = 11.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 13.sp
    ),

    )

@Immutable
data class BaubapTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val h6: TextStyle,
    val subtitle1: TextStyle,
    val subtitle2: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val body3: TextStyle,
    val text1: TextStyle,
    val text2: TextStyle,
    val text3: TextStyle,
    val text1Bold: TextStyle,
    val text2Bold: TextStyle,
    val text3Bold: TextStyle,
    val button: TextStyle,
    val caption: TextStyle,
    val overline: TextStyle
)

internal val LocalBaubapTypographies = compositionLocalOf<BaubapTypography> {
    error("No BaubapTypography provided")
}

val mdTypography = Typography(
    h1 = baubapTypography.h1,
    h2 = baubapTypography.h2,
    h3 = baubapTypography.h3,
    h4 = baubapTypography.h4,
    h5 = baubapTypography.h5,
    h6 = baubapTypography.h6,
    subtitle1 = baubapTypography.subtitle1,
    subtitle2 = baubapTypography.subtitle2,
    body1 = baubapTypography.text1,
    body2 = baubapTypography.text2,
    button = baubapTypography.button,
    caption = baubapTypography.caption,
    overline = baubapTypography.overline
)
