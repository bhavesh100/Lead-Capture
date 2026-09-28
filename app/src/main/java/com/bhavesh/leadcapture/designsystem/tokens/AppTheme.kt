package com.bhavesh.leadcapture.designsystem.tokens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalAppColors = staticCompositionLocalOf { lightAppColors }
val LocalAppSpacing = staticCompositionLocalOf { defaultAppSpacing }
val LocalAppTypography = staticCompositionLocalOf { defaultAppTypography }

@Composable
fun AppTheme(
    colors: AppColors = lightAppColors,
    spacing: AppSpacing = defaultAppSpacing,
    typography: AppTypography = defaultAppTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppSpacing provides spacing,
        LocalAppTypography provides typography,
        content = content
    )
}

object AppTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current

    val spacing: AppSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalAppSpacing.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current
}
