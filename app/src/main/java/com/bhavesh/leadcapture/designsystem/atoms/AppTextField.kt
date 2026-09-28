package com.bhavesh.leadcapture.designsystem.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import com.bhavesh.leadcapture.designsystem.tokens.AppTheme

@Composable
public fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String? = null,
    errorText: String? = null,
    onBlur: () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }

    val hasError = !errorText.isNullOrEmpty()
    val borderColor = when {
        hasError -> AppTheme.colors.error
        isFocused -> AppTheme.colors.borderFocused
        else -> AppTheme.colors.border
    }
    val borderThickness = if (isFocused) {
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
                .onFocusChanged { focusState ->
                    if (isFocused && !focusState.isFocused) {
                        onBlur()
                    }
                    isFocused = focusState.isFocused
                }
                .padding(horizontal = AppTheme.spacing.md, vertical = AppTheme.spacing.sm),
            contentAlignment = Alignment.CenterStart
        ) {
            if (value.isEmpty() && !placeholder.isNullOrEmpty()) {
                BasicText(
                    text = placeholder,
                    style = AppTheme.typography.hint.copy(color = AppTheme.colors.textSecondary)
                )
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = AppTheme.typography.body.copy(color = AppTheme.colors.onSurface),
                cursorBrush = SolidColor(AppTheme.colors.primary),
                keyboardOptions = keyboardOptions,
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (hasError) {
            Spacer(modifier = Modifier.height(AppTheme.spacing.xs))
            AppFieldError(errorText = errorText!!)
        }
    }
}
