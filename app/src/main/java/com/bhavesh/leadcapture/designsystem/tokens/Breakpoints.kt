package com.bhavesh.leadcapture.designsystem.tokens

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val TWO_COLUMN_MIN_WIDTH = 600.dp
val MAX_CONTENT_WIDTH = 840.dp

enum class WidthClass { Compact, Expanded }

fun widthClassFor(maxWidth: Dp): WidthClass = 
    if (maxWidth >= TWO_COLUMN_MIN_WIDTH) WidthClass.Expanded else WidthClass.Compact
