package com.bhavesh.leadcapture.designsystem.form.molecules

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.bhavesh.leadcapture.designsystem.atoms.AppCheckboxField
import com.bhavesh.leadcapture.designsystem.atoms.AppSelectField
import com.bhavesh.leadcapture.designsystem.atoms.AppTextField
import com.bhavesh.leadcapture.designsystem.atoms.SelectOption
import com.bhavesh.leadcapture.designsystem.form.model.FieldConfig
import com.bhavesh.leadcapture.designsystem.form.model.FieldType
import com.bhavesh.leadcapture.designsystem.form.model.FieldValue

@Composable
public fun FieldRenderer(
    field: FieldConfig,
    value: FieldValue?,
    error: String?,
    onValueChange: (FieldValue) -> Unit,
    onBlur: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (field.type) {
        FieldType.TEXT -> {
            AppTextField(
                value = (value as? FieldValue.Text)?.value.orEmpty(),
                onValueChange = { onValueChange(FieldValue.Text(it)) },
                label = field.label,
                placeholder = field.placeholder,
                errorText = error,
                onBlur = onBlur,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = modifier
            )
        }
        FieldType.EMAIL -> {
            AppTextField(
                value = (value as? FieldValue.Text)?.value.orEmpty(),
                onValueChange = { onValueChange(FieldValue.Text(it)) },
                label = field.label,
                placeholder = field.placeholder,
                errorText = error,
                onBlur = onBlur,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = modifier
            )
        }
        FieldType.PHONE -> {
            AppTextField(
                value = (value as? FieldValue.Text)?.value.orEmpty(),
                onValueChange = { onValueChange(FieldValue.Text(it)) },
                label = field.label,
                placeholder = field.placeholder,
                errorText = error,
                onBlur = onBlur,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = modifier
            )
        }
        FieldType.SELECT -> {
            val selectOptions = field.options.map { SelectOption(it.label, it.value) }
            val selectedLabel = selectOptions.find { it.value == (value as? FieldValue.Selected)?.value }?.label
            
            AppSelectField(
                selectedLabel = selectedLabel,
                options = selectOptions,
                onOptionSelected = { option -> onValueChange(FieldValue.Selected(option.value)) },
                label = field.label,
                placeholder = field.placeholder,
                errorText = error,
                onBlur = onBlur,
                modifier = modifier
            )
        }
        FieldType.CHECKBOX -> {
            AppCheckboxField(
                checked = (value as? FieldValue.Checked)?.value ?: false,
                onCheckedChange = { onValueChange(FieldValue.Checked(it)) },
                label = field.label,
                errorText = error,
                modifier = modifier
            )
        }
    }
}
