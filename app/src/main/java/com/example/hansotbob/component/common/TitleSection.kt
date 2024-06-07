package com.example.hansotbob.component.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BasicTitleSection() {
    Column {
        Text(
            text = "한솥밥",
            style = MaterialTheme.typography.headlineLarge.copy(color = MaterialTheme.colorScheme.primary)
        )
        Text(
            text = "집밥공유 플랫폼",
            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.secondary)
        )
    }
}