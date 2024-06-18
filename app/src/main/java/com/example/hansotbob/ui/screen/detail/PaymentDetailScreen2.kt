package com.example.hansotbob.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
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
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.hansotbob.R
import com.example.hansotbob.ui.theme.PrimaryColor

@Composable
fun PaymentDetailScreen2() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        val (topBar, paymentDetails, buyButton) = createRefs()
           
        // Top Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(PrimaryColor)
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                }
        ) {
            // Back Button
            Image(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "Back Button",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { /* Handle back button click */ }
            )

            // Title
            Text(
                text = "판매 내역",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
        }

        // Centered Payment Details
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(paymentDetails) {
                    top.linkTo(topBar.bottom)
                    bottom.linkTo(buyButton.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "총합계: ",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )

                Text(
                    text = "금액",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )

                Text(
                    text = "이 구매자에게 전달됩니다.",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
            }
        }

        // Buy Button
        Button(
            onClick = { /* Handle buy button click */ },
            colors = ButtonDefaults.buttonColors(PrimaryColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(buyButton) {
                    bottom.linkTo(parent.bottom, margin = 32.dp)
                }
        ) {
            Text(
                text = "구매확정하기",
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentDetailScreen2Preview() {
    PaymentDetailScreen2()
}
