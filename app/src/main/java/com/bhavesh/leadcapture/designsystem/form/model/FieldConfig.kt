package com.bhavesh.leadcapture.designsystem.form.model

public data class FieldConfig(
    val name: String,
    val label: String,
    val type: FieldType,
    val span: FieldSpan = FieldSpan.FULL,
    val placeholder: String? = null,
    val options: List<Option> = emptyList(),
    val rules: List<Rule> = emptyList(),
    val visibleWhen: VisibleWhen? = null
)
