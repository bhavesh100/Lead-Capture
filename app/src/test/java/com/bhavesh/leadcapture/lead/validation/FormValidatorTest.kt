package com.bhavesh.leadcapture.lead.validation

import com.bhavesh.leadcapture.designsystem.form.model.FieldConfig
import com.bhavesh.leadcapture.designsystem.form.model.FieldSpan
import com.bhavesh.leadcapture.designsystem.form.model.FieldType
import com.bhavesh.leadcapture.designsystem.form.model.FieldValue
import com.bhavesh.leadcapture.designsystem.form.model.Option
import com.bhavesh.leadcapture.designsystem.form.model.Rule
import com.bhavesh.leadcapture.designsystem.form.model.VisibleWhen
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

public class FormValidatorTest {

    @Test
    public fun testRequiredText() {
        val config = listOf(
            FieldConfig(
                name = "fullName",
                label = "Full Name",
                type = FieldType.TEXT,
                rules = listOf(Rule.Required("Name is required"))
            )
        )

        // Missing value
        val emptyErrors = validate(config, emptyMap())
        assertEquals("Name is required", emptyErrors["fullName"])

        // Blank value
        val blankErrors = validate(config, mapOf("fullName" to FieldValue.Text("   ")))
        assertEquals("Name is required", blankErrors["fullName"])

        // Valid value
        val validErrors = validate(config, mapOf("fullName" to FieldValue.Text("John Doe")))
        assertTrue(validErrors.isEmpty())
    }

    @Test
    public fun testRequiredCheckbox() {
        val config = listOf(
            FieldConfig(
                name = "terms",
                label = "Accept Terms",
                type = FieldType.CHECKBOX,
                rules = listOf(Rule.Required("Must accept terms"))
            )
        )

        // Unchecked value
        val uncheckedErrors = validate(config, mapOf("terms" to FieldValue.Checked(false)))
        assertEquals("Must accept terms", uncheckedErrors["terms"])

        // Checked value
        val checkedErrors = validate(config, mapOf("terms" to FieldValue.Checked(true)))
        assertTrue(checkedErrors.isEmpty())
    }

    @Test
    public fun testEmailValidAndInvalid() {
        val config = listOf(
            FieldConfig(
                name = "email",
                label = "Email Address",
                type = FieldType.EMAIL,
                rules = listOf(Rule.Email("Invalid email format"))
            )
        )

        // Invalid email
        val invalidErrors = validate(config, mapOf("email" to FieldValue.Text("invalid-email")))
        assertEquals("Invalid email format", invalidErrors["email"])

        // Valid email
        val validErrors = validate(config, mapOf("email" to FieldValue.Text("user@example.com")))
        assertTrue(validErrors.isEmpty())
    }

    @Test
    public fun testExactDigits() {
        val config = listOf(
            FieldConfig(
                name = "phone",
                label = "Phone Number",
                type = FieldType.PHONE,
                rules = listOf(Rule.ExactDigits(10, "Must be exactly 10 digits"))
            )
        )

        // Short digits
        val shortErrors = validate(config, mapOf("phone" to FieldValue.Text("12345")))
        assertEquals("Must be exactly 10 digits", shortErrors["phone"])

        // Non-digit characters
        val nonDigitErrors = validate(config, mapOf("phone" to FieldValue.Text("12345abcde")))
        assertEquals("Must be exactly 10 digits", nonDigitErrors["phone"])

        // Valid 10 digits
        val validErrors = validate(config, mapOf("phone" to FieldValue.Text("9876543210")))
        assertTrue(validErrors.isEmpty())
    }

    @Test
    public fun testMaxLength() {
        val config = listOf(
            FieldConfig(
                name = "code",
                label = "Promo Code",
                type = FieldType.TEXT,
                rules = listOf(Rule.MaxLength(5, "Maximum 5 characters"))
            )
        )

        // Exceeds max length
        val longErrors = validate(config, mapOf("code" to FieldValue.Text("123456")))
        assertEquals("Maximum 5 characters", longErrors["code"])

        // Equal to max length
        val validErrors = validate(config, mapOf("code" to FieldValue.Text("12345")))
        assertTrue(validErrors.isEmpty())
    }

    @Test
    public fun testHiddenFieldRuleSkipped() {
        val config = listOf(
            FieldConfig(
                name = "employmentStatus",
                label = "Employment Status",
                type = FieldType.SELECT,
                options = listOf(Option("Employed", "EMPLOYED"), Option("Unemployed", "UNEMPLOYED"))
            ),
            FieldConfig(
                name = "companyName",
                label = "Company Name",
                type = FieldType.TEXT,
                rules = listOf(Rule.Required("Company name is required")),
                visibleWhen = VisibleWhen("employmentStatus", "EMPLOYED")
            )
        )

        // Status is UNEMPLOYED, so companyName field is hidden
        val values = mapOf(
            "employmentStatus" to FieldValue.Selected("UNEMPLOYED"),
            "companyName" to FieldValue.Text("")
        )

        val errors = validate(config, values)
        assertFalse(errors.containsKey("companyName"))
        assertTrue(errors.isEmpty())
    }

    @Test
    public fun testFirstErrorWinsPerField() {
        val config = listOf(
            FieldConfig(
                name = "email",
                label = "Email Address",
                type = FieldType.EMAIL,
                rules = listOf(
                    Rule.Required("Email is required"),
                    Rule.Email("Invalid email format")
                )
            )
        )

        // Empty string fails Required first
        val errors = validate(config, mapOf("email" to FieldValue.Text("")))
        assertEquals("Email is required", errors["email"])
    }
}
