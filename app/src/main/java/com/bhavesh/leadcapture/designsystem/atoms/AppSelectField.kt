package com.bhavesh.leadcapture.designsystem.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.window.Popup
import com.bhavesh.leadcapture.designsystem.tokens.AppTheme

public data class SelectOption(
    val label: String,
    val value: String
)

@Composable
public fun AppSelectField(
    selectedLabel: String?,
    options: List<SelectOption>,
    onOptionSelected: (SelectOption) -> Unit,
    label: String,
    placeholder: String? = "Select an option",
    errorText: String? = null,
    onBlur: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    val hasError = !errorText.isNullOrEmpty()
    val borderColor = when {
        hasError -> AppTheme.colors.error
        isExpanded -> AppTheme.colors.borderFocused
        else -> AppTheme.colors.border
    }
    val borderThickness = if (isExpanded) {
        AppTheme.spacing.borderThicknessFocused
    } else {
        AppTheme.spacing.borderThickness
    }

    val shape = RoundedCornerShape(AppTheme.spacing.radiusSmall)

    Column(modifier = modifier) {
        BasicText(
            text = label,
            style = AppTheme.typography.label.copy(color = AppTheme.colors.onSurface)
        )
        Spacer(modifier = Modifier.height(AppTheme.spacing.xs))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = borderThickness, color = borderColor, shape = shape)
                .background(color = AppTheme.colors.surface, shape = shape)
                .clickable {
                    val wasExpanded = isExpanded
                    isExpanded = !isExpanded
                    if (wasExpanded) {
                        onBlur()
                    }
                }
                .padding(horizontal = AppTheme.spacing.md, vertical = AppTheme.spacing.md)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val displayText = selectedLabel ?: placeholder ?: ""
                val textStyle = if (selectedLabel != null) {
                    AppTheme.typography.body.copy(color = AppTheme.colors.onSurface)
                } else {
                    AppTheme.typography.hint.copy(color = AppTheme.colors.textSecondary)
                }

                BasicText(
                    text = displayText,
                    style = textStyle,
                    modifier = Modifier.weight(1f)
                )
                BasicText(
                    text = if (isExpanded) "▲" else "▼",
                    style = AppTheme.typography.hint.copy(color = AppTheme.colors.textSecondary)
                )
            }

            if (isExpanded) {
                Popup(
                    onDismissRequest = {
                        isExpanded = false
                        onBlur()
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(elevation = AppTheme.spacing.popupElevation, shape = shape)
                            .border(width = AppTheme.spacing.borderThickness, color = AppTheme.colors.border, shape = shape)
                            .background(color = AppTheme.colors.surface, shape = shape)
                    ) {
                        options.forEach { option ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onOptionSelected(option)
                                        isExpanded = false
                                        onBlur()
                                    }
                                    .padding(horizontal = AppTheme.spacing.md, vertical = AppTheme.spacing.md)
                            ) {
                                BasicText(
                                    text = option.label,
                                    style = AppTheme.typography.body.copy(color = AppTheme.colors.onSurface)
                                )
                            }
                        }
                    }
                }
            }
        }
        if (hasError) {
            Spacer(modifier = Modifier.height(AppTheme.spacing.xs))
            AppFieldError(errorText = errorText!!)
        }
    }
}
