package com.bhavesh.leadcapture.lead.state

import androidx.lifecycle.ViewModel
import com.bhavesh.leadcapture.designsystem.form.model.FieldConfig
import com.bhavesh.leadcapture.designsystem.form.model.FieldValue
import com.bhavesh.leadcapture.designsystem.form.model.FormValues
import com.bhavesh.leadcapture.lead.validation.validate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

public class LeadViewModel : ViewModel() {

    private val _config = MutableStateFlow<List<FieldConfig>>(emptyList())
    public val config: StateFlow<List<FieldConfig>> = _config.asStateFlow()

    private val _values = MutableStateFlow<FormValues>(emptyMap())
    public val values: StateFlow<FormValues> = _values.asStateFlow()

    private val _errors = MutableStateFlow<Map<String, String>>(emptyMap())
    public val errors: StateFlow<Map<String, String>> = _errors.asStateFlow()

    private val _submittedValues = MutableStateFlow<FormValues?>(null)
    public val submittedValues: StateFlow<FormValues?> = _submittedValues.asStateFlow()

    public fun loadConfig(formConfig: List<FieldConfig>) {
        _config.value = formConfig
        _values.value = emptyMap()
        _errors.value = emptyMap()
        _submittedValues.value = null
    }

    public fun onValueChange(name: String, value: FieldValue) {
        _values.update { current ->
            current.toMutableMap().apply { put(name, value) }
        }
        _errors.update { current ->
            current.toMutableMap().apply { remove(name) }
        }
    }

    public fun onBlur(name: String) {
        val currentErrors = validate(_config.value, _values.value)
        val fieldError = currentErrors[name]
        
        _errors.update { current ->
            val newErrors = current.toMutableMap()
            if (fieldError != null) {
                newErrors[name] = fieldError
            } else {
                newErrors.remove(name)
            }
            newErrors
        }
    }

    public fun onSubmit() {
        val validationErrors = validate(_config.value, _values.value)
        if (validationErrors.isEmpty()) {
            _errors.value = emptyMap()
            _submittedValues.value = _values.value
        } else {
            _errors.value = validationErrors
        }
    }
}
