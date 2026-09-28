package com.bhavesh.leadcapture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import com.bhavesh.leadcapture.designsystem.tokens.AppTheme
import com.bhavesh.leadcapture.lead.state.LeadViewModel
import com.bhavesh.leadcapture.lead.ui.LeadScreen

class MainActivity : ComponentActivity() {
    private val viewModel: LeadViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                LeadScreen(
                    viewModel = viewModel,
                    modifier = Modifier.safeDrawingPadding()
                )
            }
        }
    }
}