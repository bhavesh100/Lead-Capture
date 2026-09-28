package com.bhavesh.leadcapture.designsystem.tokens

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class AppTypography(
    val title: TextStyle,
    val label: TextStyle,
    val body: TextStyle,
    val hint: TextStyle,
    val error: TextStyle
)

val defaultAppTypography = AppTypography(
    title = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    ),
    label = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    ),
    body = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    hint = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    error = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    )
)
