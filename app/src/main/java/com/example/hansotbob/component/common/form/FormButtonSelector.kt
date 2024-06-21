package com.example.hansotbob.component.common.form

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormButtonSelector(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(label, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp) // 4dp space between buttons
        ) {
            options.forEach { option ->
                OutlinedButton(
                    onClick = { onOptionSelected(option) },
                    border = BorderStroke(1.dp, if (selectedOption == option) Color.DarkGray else Color.Gray),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (selectedOption == option) Color.DarkGray else Color.Transparent,
                        contentColor = if (selectedOption == option) Color.White else Color.Gray
                    ),
                    shape = RoundedCornerShape(50) // Rounded corners to match the design
                ) {
                    Text(option, fontSize = 16.sp)
                }
            }
        }
    }
}
