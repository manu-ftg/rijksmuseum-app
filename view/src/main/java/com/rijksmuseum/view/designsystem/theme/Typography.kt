package com.rijksmuseum.view.designsystem.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 60.sp
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 48.sp
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    ),
    button = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    )
)
