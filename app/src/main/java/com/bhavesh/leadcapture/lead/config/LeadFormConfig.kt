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
            name = "firstName",
            label = "First Name",
            type = FieldType.TEXT,
            span = FieldSpan.HALF,
            rules = listOf(Rule.Required("First name is required"))
        ),
        FieldConfig(
            name = "lastName",
            label = "Last Name",
            type = FieldType.TEXT,
            span = FieldSpan.HALF,
            rules = listOf(Rule.Required("Last name is required"))
        ),
        FieldConfig(
            name = "email",
            label = "Email Address",
            type = FieldType.EMAIL,
            rules = listOf(
                Rule.Required("Email is required"),
                Rule.Email("Please enter a valid email")
            )
        ),
        FieldConfig(
            name = "phone",
            label = "Phone Number",
            type = FieldType.PHONE,
            rules = listOf(Rule.ExactDigits(10, "Phone number must be exactly 10 digits"))
        ),
        FieldConfig(
            name = "role",
            label = "Role",
            type = FieldType.SELECT,
            options = listOf(
                Option("Developer", "DEV"),
                Option("Designer", "DESIGN"),
                Option("Manager", "MGR")
            ),
            rules = listOf(Rule.Required("Please select a role"))
        ),
        FieldConfig(
            name = "company",
            label = "Company Name",
            type = FieldType.TEXT,
            visibleWhen = VisibleWhen("role", "MGR"),
            rules = listOf(Rule.Required("Company name is required for Managers"))
        ),
        FieldConfig(
            name = "terms",
            label = "I agree to the terms and conditions",
            type = FieldType.CHECKBOX,
            rules = listOf(Rule.Required("You must agree to the terms"))
        )
    )
}
