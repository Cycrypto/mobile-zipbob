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
import androidx.compose.ui.unit.dp

@Composable
fun MealkitsDetailScreen(title: String, recruit: String, place: String, price: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = recruit, style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = place, style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = price, style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.height(8.dp))
//        Image(painter = painterResource(id = imageRes.toInt()), contentDescription = null)
    }
}