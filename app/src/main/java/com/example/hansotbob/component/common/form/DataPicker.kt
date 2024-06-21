package com.example.hansotbob.component.common.form

import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

@Composable
fun DatePicker(selectedDate: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var dateText by remember { mutableStateOf(selectedDate) }

    ClickableText(
        text = AnnotatedString(dateText.ifEmpty { "날짜 선택" }),
        onClick = {
            Log.d("DatePicker", "Text clicked")
            DatePickerDialog(context, { _, y, m, d ->
                dateText = "$y-${m + 1}-$d"
                onDateSelected(dateText)
            }, year, month, day).show()
        },
        style = TextStyle(
            color = Color.Black,
            fontSize = 16.sp
        ),
        modifier = Modifier
            .fillMaxWidth()

            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(4.dp))
            .padding(16.dp)
    )
}