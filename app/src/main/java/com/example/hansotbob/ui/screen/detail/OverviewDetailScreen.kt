package com.example.hansotbob.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hansotbob.ui.theme.HansotbobTheme

@Composable
fun OverviewDetailScreen(name: String, category: String, imageRes: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = name, style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = category, style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.height(8.dp))
        Image(painter = painterResource(id = imageRes.toInt()), contentDescription = null)
    }
}