package com.bhavesh.leadcapture.designsystem.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.bhavesh.leadcapture.designsystem.tokens.AppTheme

@Composable
public fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val backgroundColor = if (enabled) AppTheme.colors.primary else AppTheme.colors.disabled
    val textColor = if (enabled) AppTheme.colors.onPrimary else AppTheme.colors.textSecondary
    val shape = RoundedCornerShape(AppTheme.spacing.radiusMedium)

    Box(
        modifier = modifier
            .defaultMinSize(minHeight = AppTheme.spacing.buttonMinHeight)
            .clip(shape)
            .background(backgroundColor)
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = AppTheme.spacing.lg, vertical = AppTheme.spacing.md),
        contentAlignment = Alignment.Center
    ) {
        BasicText(
            text = text,
            style = AppTheme.typography.label.copy(color = textColor)
        )
    }
}
