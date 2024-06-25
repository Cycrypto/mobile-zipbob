package com.example.hansotbob.ui.screen.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hansotbob.R
import com.example.hansotbob.ui.theme.PrimaryColor

@Composable
fun MyPageProfileEditScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(PrimaryColor)
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            // Back Button
            Image(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "Back Button",
                modifier = Modifier
                    .wrapContentSize()
                    .clickable{ navController.popBackStack() }

            )

            // Title
            Text(
                text = "프로필 수정",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // User Image
        Image(
            painter = painterResource(id = R.drawable.ic_mypage),
            contentDescription = "User Image",
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Nickname Label
        Text(
            text = "닉네임",
            fontSize = 16.sp,
            modifier = Modifier
                .padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Nickname EditText
        TextField(
            value = "현재 닉네임",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            placeholder = { Text("현재 닉네임") },
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Save Button
        Button(
            onClick = { /* Save action */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = buttonColors(PrimaryColor)
        ) {
            Text(
                text = "프로필 수정",
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MyPageProfileEditScreenPreview() {
//    MyPageProfileEditScreen()
//}