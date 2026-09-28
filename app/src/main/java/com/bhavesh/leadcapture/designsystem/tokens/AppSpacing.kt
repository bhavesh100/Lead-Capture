package com.bhavesh.leadcapture.designsystem.tokens

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppSpacing(
    val xs: Dp = 4.dp,
    val sm: Dp = 8.dp,
    val md: Dp = 12.dp,
    val lg: Dp = 16.dp,
    val xl: Dp = 24.dp,
    val xxl: Dp = 32.dp,
    val fieldGap: Dp = 16.dp,
    val columnGap: Dp = 16.dp,
    val borderThickness: Dp = 1.dp,
    val borderThicknessFocused: Dp = 2.dp,
    val radiusSmall: Dp = 4.dp,
    val radiusMedium: Dp = 8.dp,
    val buttonMinHeight: Dp = 48.dp,
    val fieldMinHeight: Dp = 48.dp,
    val checkboxSize: Dp = 20.dp,
    val iconSize: Dp = 16.dp,
    val popupElevation: Dp = 4.dp
)

val defaultAppSpacing = AppSpacing()
