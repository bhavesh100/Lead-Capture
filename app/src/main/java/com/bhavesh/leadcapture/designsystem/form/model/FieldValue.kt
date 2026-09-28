package com.bhavesh.leadcapture.designsystem.form.model

public sealed interface FieldValue {
    public data class Text(val value: String) : FieldValue
    public data class Selected(val value: String) : FieldValue
    public data class Checked(val value: Boolean) : FieldValue
}

public typealias FormValues = Map<String, FieldValue>
