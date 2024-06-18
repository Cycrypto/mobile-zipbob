package com.example.hansotbob.component.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonBar(onContactSellerClick: () -> Unit, onBuyClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = onContactSellerClick,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text("판매자에게 연락", color = Color.White)
        }
        Button(
            onClick = onBuyClick,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text("구매", color = Color.White)
        }
    }
}
