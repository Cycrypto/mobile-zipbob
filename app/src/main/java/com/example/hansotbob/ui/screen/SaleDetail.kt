package com.example.hansotbob.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hansotbob.data.PaymentDetail

@Composable
fun SaleDetail(
    paymentDetail: PaymentDetail,
    modifier: Modifier = Modifier
) {
    val totalPrice = paymentDetail.itemCounts * paymentDetail.itemPrice
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White)
    ) {
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
}

@Preview(showBackground = true)
@Composable
fun SaleDetailPreview() {
    SaleDetail(
        paymentDetail = PaymentDetail(
            productName = "상품명",
            itemCounts = 2,
            itemPrice = 1000,
            seller = "감자"
        )
    )
}
