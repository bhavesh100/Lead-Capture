package com.bhavesh.leadcapture.lead.validation

import com.bhavesh.leadcapture.designsystem.form.model.FieldConfig
import com.bhavesh.leadcapture.designsystem.form.model.FieldValue
import com.bhavesh.leadcapture.designsystem.form.model.FormValues
import com.bhavesh.leadcapture.designsystem.form.model.Rule
import com.bhavesh.leadcapture.designsystem.form.model.isVisible

private val EMAIL_REGEX = Regex("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+\$")

public fun validate(config: List<FieldConfig>, values: FormValues): Map<String, String> {
    val errors = mutableMapOf<String, String>()

    for (field in config) {
        if (!isVisible(field, values)) {
            continue
        }

        val value = values[field.name]

        for (rule in field.rules) {
            val errorMsg = validateRule(rule, value)
            if (errorMsg != null) {
                errors[field.name] = errorMsg
                break
            }
        }
    }

    return errors
}

private fun validateRule(rule: Rule, fieldValue: FieldValue?): String? {
    return when (rule) {
        is Rule.Required -> {
            when (fieldValue) {
                null -> rule.message
                is FieldValue.Text -> if (fieldValue.value.isBlank()) rule.message else null
                is FieldValue.Selected -> if (fieldValue.value.isBlank()) rule.message else null
                is FieldValue.Checked -> if (!fieldValue.value) rule.message else null
            }
        }
        is Rule.Email -> {
            val text = (fieldValue as? FieldValue.Text)?.value.orEmpty()
            if (text.isNotEmpty() && !EMAIL_REGEX.matches(text)) {
                rule.message
            } else null
        }
        is Rule.ExactDigits -> {
            val text = (fieldValue as? FieldValue.Text)?.value.orEmpty()
            if (text.isNotEmpty() && (text.length != rule.count || !text.all { it.isDigit() })) {
                rule.message
            } else null
        }
        is Rule.MaxLength -> {
            val text = (fieldValue as? FieldValue.Text)?.value.orEmpty()
            if (text.length > rule.max) {
                rule.message
            } else null
        }
    }
}
