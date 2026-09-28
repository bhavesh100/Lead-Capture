package com.bhavesh.leadcapture.designsystem.form.molecules

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bhavesh.leadcapture.designsystem.form.model.FieldConfig
import com.bhavesh.leadcapture.designsystem.form.model.FieldSpan
import com.bhavesh.leadcapture.designsystem.form.model.FieldValue
import com.bhavesh.leadcapture.designsystem.form.model.FormValues
import com.bhavesh.leadcapture.designsystem.form.model.isVisible
import com.bhavesh.leadcapture.designsystem.tokens.AppTheme
import com.bhavesh.leadcapture.designsystem.tokens.TWO_COLUMN_MIN_WIDTH

@Composable
public fun FormLayout(
    fields: List<FieldConfig>,
    values: FormValues,
    errors: Map<String, String>,
    onValueChange: (String, FieldValue) -> Unit,
    onBlur: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier) {
        val isWide = maxWidth >= TWO_COLUMN_MIN_WIDTH
        val visibleFields = fields.filter { isVisible(it, values) }

        Column(modifier = Modifier.fillMaxWidth()) {
            var i = 0
            while (i < visibleFields.size) {
                val field = visibleFields[i]

                if (isWide && field.span == FieldSpan.HALF && i + 1 < visibleFields.size && visibleFields[i + 1].span == FieldSpan.HALF) {
                    val nextField = visibleFields[i + 1]
                    Row(modifier = Modifier.fillMaxWidth()) {
                        FieldRenderer(
                            field = field,
                            value = values[field.name],
                            error = errors[field.name],
                            onValueChange = { newValue -> onValueChange(field.name, newValue) },
                            onBlur = { onBlur(field.name) },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(AppTheme.spacing.columnGap))
                        FieldRenderer(
                            field = nextField,
                            value = values[nextField.name],
                            error = errors[nextField.name],
                            onValueChange = { newValue -> onValueChange(nextField.name, newValue) },
                            onBlur = { onBlur(nextField.name) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    i += 2
                } else {
                    FieldRenderer(
                        field = field,
                        value = values[field.name],
                        error = errors[field.name],
                        onValueChange = { newValue -> onValueChange(field.name, newValue) },
                        onBlur = { onBlur(field.name) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    i += 1
                }

                if (i < visibleFields.size) {
                    Spacer(modifier = Modifier.height(AppTheme.spacing.fieldGap))
                }
            }
        }
    }
}
