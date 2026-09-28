package com.bhavesh.leadcapture.designsystem.form.model

public sealed interface Rule {
    public data class Required(val message: String) : Rule
    public data class Email(val message: String) : Rule
    public data class ExactDigits(val count: Int, val message: String) : Rule
    public data class MaxLength(val max: Int, val message: String) : Rule
}
