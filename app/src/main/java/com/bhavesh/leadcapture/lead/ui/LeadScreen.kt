package com.bhavesh.leadcapture.lead.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bhavesh.leadcapture.designsystem.atoms.AppButton
import com.bhavesh.leadcapture.designsystem.form.molecules.FormLayout
import com.bhavesh.leadcapture.designsystem.tokens.AppTheme
import com.bhavesh.leadcapture.lead.config.LeadFormConfig
import com.bhavesh.leadcapture.lead.state.LeadViewModel

@Composable
public fun LeadScreen(
    viewModel: LeadViewModel,
    modifier: Modifier = Modifier
) {
    val config by viewModel.config.collectAsState()
    val values by viewModel.values.collectAsState()
    val errors by viewModel.errors.collectAsState()
    val submittedValues by viewModel.submittedValues.collectAsState()

    LaunchedEffect(Unit) {
        if (config.isEmpty()) {
            viewModel.loadConfig(LeadFormConfig.defaultConfig)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.spacing.lg)
                .verticalScroll(rememberScrollState())
        ) {
            BasicText(
                text = "Lead Capture",
                style = AppTheme.typography.title.copy(color = AppTheme.colors.onSurface)
            )
            Spacer(modifier = Modifier.height(AppTheme.spacing.xl))

            FormLayout(
                fields = config,
                values = values,
                errors = errors,
                onValueChange = viewModel::onValueChange,
                onBlur = viewModel::onBlur,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(AppTheme.spacing.xl))

            AppButton(
                text = "Submit",
                onClick = viewModel::onSubmit,
                enabled = true,
                modifier = Modifier.fillMaxWidth()
            )

            if (submittedValues != null) {
                Spacer(modifier = Modifier.height(AppTheme.spacing.xl))
                BasicText(
                    text = "Submitted Successfully!",
                    style = AppTheme.typography.title.copy(color = AppTheme.colors.primary)
                )
                Spacer(modifier = Modifier.height(AppTheme.spacing.md))
                BasicText(
                    text = submittedValues.toString(),
                    style = AppTheme.typography.body.copy(color = AppTheme.colors.onSurface)
                )
            }
        }
    }
}
