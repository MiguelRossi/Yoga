package com.example.yoga.main_ui.tools

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.example.yoga.R

@Composable
fun getNightModeFriendlyPlaceholder() = forwardingPainter(
    painter = painterResource(id = R.drawable.om),
    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary),
)
