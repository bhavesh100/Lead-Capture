package com.bhavesh.leadcapture.designsystem.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.bhavesh.leadcapture.designsystem.tokens.AppTheme

@Composable
public fun AppCheckboxField(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    errorText: String? = null,
    modifier: Modifier = Modifier
) {
    val hasError = !errorText.isNullOrEmpty()
    val borderColor = if (hasError) AppTheme.colors.error else AppTheme.colors.border
    val shape = RoundedCornerShape(AppTheme.spacing.radiusSmall)

    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { onCheckedChange(!checked) }
        ) {
            Box(
                modifier = Modifier
                    .size(AppTheme.spacing.checkboxSize)
                    .clip(shape)
                    .background(
                        color = if (checked) AppTheme.colors.primary else AppTheme.colors.surface
                    )
                    .border(
                        width = AppTheme.spacing.borderThickness,
                        color = if (checked) AppTheme.colors.primary else borderColor,
                        shape = shape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (checked) {
                    BasicText(
                        text = "✓",
                        style = AppTheme.typography.label.copy(color = AppTheme.colors.onPrimary)
                    )
                }
            }
            Spacer(modifier = Modifier.width(AppTheme.spacing.sm))
            BasicText(
                text = label,
                style = AppTheme.typography.body.copy(color = AppTheme.colors.onSurface)
            )
        }
        if (hasError) {
            Spacer(modifier = Modifier.height(AppTheme.spacing.xs))
            AppFieldError(errorText = errorText!!)
        }
    }
}
