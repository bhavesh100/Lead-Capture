package com.bhavesh.leadcapture.designsystem.tokens

import androidx.compose.ui.graphics.Color

data class AppColors(
    val background: Color,
    val surface: Color,
    val onSurface: Color,
    val textSecondary: Color,
    val border: Color,
    val borderFocused: Color,
    val primary: Color,
    val onPrimary: Color,
    val error: Color,
    val disabled: Color
)

val lightAppColors = AppColors(
    background = Color(0xFFF8F9FA),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF212529),
    textSecondary = Color(0xFF6C757D),
    border = Color(0xFFDEE2E6),
    borderFocused = Color(0xFF0D6EFD),
    primary = Color(0xFF0D6EFD),
    onPrimary = Color(0xFFFFFFFF),
    error = Color(0xFFDC3545),
    disabled = Color(0xFFE9ECEF)
)
