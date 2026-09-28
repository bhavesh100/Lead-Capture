package com.bhavesh.leadcapture.designsystem.form.model

public data class VisibleWhen(
    val dependsOn: String,
    val equalsValue: String
)

public fun isVisible(field: FieldConfig, values: FormValues): Boolean {
    val rule = field.visibleWhen ?: return true
    val targetVal = values[rule.dependsOn] ?: return false
    return when (targetVal) {
        is FieldValue.Text -> targetVal.value == rule.equalsValue
        is FieldValue.Selected -> targetVal.value == rule.equalsValue
        is FieldValue.Checked -> targetVal.value.toString() == rule.equalsValue
    }
}
