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
    val columnGap: Dp = 16.dp
)

val defaultAppSpacing = AppSpacing()
