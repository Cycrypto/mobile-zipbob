package com.example.hansotbob.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.R
import com.example.hansotbob.ui.theme.PrimaryColor
import com.example.hansotbob.data.PaymentDetail

@Composable
fun PaymentDetailScreen(navController: NavHostController) {
    val paymentDetails = listOf(
        PaymentDetail(
            productName = "물품명 1",
            itemCounts = "1",
            itemPrice = "1000"
        ),
        PaymentDetail(
            productName = "물품명 2",
            itemCounts = "2",
            itemPrice = "2000"
        ),
        PaymentDetail(
            productName = "물품명 3",
            itemCounts = "3",
            itemPrice = "3000"
        )
    )

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        val (topBar, paymentTitle, recyclerView, totalPrice, buyButton) = createRefs()

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
                text = "구매 내역",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
        }

        // Payment Title
        Text(
            text = "결제항목",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 16.dp, top = 32.dp)
                .constrainAs(paymentTitle) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                }
        )

        // RecyclerView (or LazyColumn in Compose)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color.White)
                .constrainAs(recyclerView) {
                    top.linkTo(paymentTitle.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(totalPrice.top, margin = 16.dp)
                }
        ) {
            items(paymentDetails) { paymentDetail ->
                PaymentDetail(paymentDetail = paymentDetail)
            }
        }

        // Total Price
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(totalPrice) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(buyButton.top, margin = 16.dp)
                }
        ) {
            Text(
                text = "총합계: ",
                fontSize = 18.sp,
                modifier = Modifier
                    .weight(1f)
            )

            Text(
                text = "금액",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }

        // Buy Button
        Button(
            onClick = { navController.navigate("paymentDetail2") },
            colors = ButtonDefaults.buttonColors(PrimaryColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(buyButton) {
                    bottom.linkTo(parent.bottom, margin = 32.dp)
                }
        ) {
            Text(
                text = "구매하기",
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentDetailScreenPreview() {
    PaymentDetailScreen(rememberNavController())
}
