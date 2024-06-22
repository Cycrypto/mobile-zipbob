package com.example.hansotbob.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hansotbob.data.PaymentDetail

@Composable
fun PaymentDetail(
    paymentDetail: PaymentDetail,
    modifier: Modifier = Modifier
) {
    val totalPrice = paymentDetail.itemCounts * paymentDetail.itemPrice
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = paymentDetail.productName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
            )

            Text(
                text = "${paymentDetail.itemCounts} 개",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 16.dp)
            )

            Text(
                text = "가격: ${totalPrice}pt",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "판매자: ${paymentDetail.seller} 님",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentDetailPreview() {
    PaymentDetail(
        paymentDetail = PaymentDetail(
            productName = "상품명",
            itemCounts = 2,
            itemPrice = 1000,
            seller = "감자"
        )
    )
}
