package com.bhavesh.leadcapture.lead.config

import com.bhavesh.leadcapture.designsystem.form.model.FieldConfig
import com.bhavesh.leadcapture.designsystem.form.model.FieldSpan
import com.bhavesh.leadcapture.designsystem.form.model.FieldType
import com.bhavesh.leadcapture.designsystem.form.model.Option
import com.bhavesh.leadcapture.designsystem.form.model.Rule
import com.bhavesh.leadcapture.designsystem.form.model.VisibleWhen

public object LeadFormConfig {
    public val defaultConfig: List<FieldConfig> = listOf(
        FieldConfig(
            name = "fullName",
            label = "Full name",
            type = FieldType.TEXT,
            span = FieldSpan.HALF,
            rules = listOf(Rule.Required("Full name is required"))
        ),
        FieldConfig(
            name = "email",
            label = "Email",
            type = FieldType.EMAIL,
            span = FieldSpan.HALF,
            rules = listOf(
                Rule.Required("Email is required"),
                Rule.Email("Please enter a valid email")
            )
        ),
        FieldConfig(
            name = "leadType",
            label = "Lead type",
            type = FieldType.SELECT,
            options = listOf(
                Option("Individual", "INDIVIDUAL"),
                Option("Company", "COMPANY")
            ),
            rules = listOf(Rule.Required("Please select a lead type"))
        ),
        FieldConfig(
            name = "companyName",
            label = "Company name",
            type = FieldType.TEXT,
            visibleWhen = VisibleWhen("leadType", "COMPANY"),
            rules = listOf(Rule.Required("Company name is required"))
        ),
        FieldConfig(
            name = "phone",
            label = "Phone",
            type = FieldType.TEXT,
            rules = listOf(
                Rule.Required("Phone number is required"),
                Rule.ExactDigits(10, "Phone must be exactly 10 digits")
            )
        ),
        FieldConfig(
            name = "notes",
            label = "Notes",
            type = FieldType.TEXTAREA,
            rules = listOf(Rule.MaxLength(200, "Notes cannot exceed 200 characters"))
        ),
        FieldConfig(
            name = "consent",
            label = "Consent",
            type = FieldType.CHECKBOX,
            rules = listOf(Rule.Required("You must provide consent"))
        )
    )
}
