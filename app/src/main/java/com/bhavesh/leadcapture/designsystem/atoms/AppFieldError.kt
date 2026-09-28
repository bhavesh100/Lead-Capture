package com.bhavesh.leadcapture.designsystem.atoms

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bhavesh.leadcapture.designsystem.tokens.AppTheme

@Composable
public fun AppFieldError(
    errorText: String,
    modifier: Modifier = Modifier
) {
    BasicText(
        text = errorText,
        style = AppTheme.typography.error.copy(color = AppTheme.colors.error),
        modifier = modifier
    )
}
