package com.example.hansotbob.ui.screen.detail

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
fun PaymentDetail(
    paymentDetail: PaymentDetail,
    modifier: Modifier = Modifier
) {
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
            text = paymentDetail.itemCounts,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(start = 16.dp)
        )

        Text(
            text = paymentDetail.itemPrice,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(start = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentDetailPreview() {
    PaymentDetail(
        paymentDetail = PaymentDetail(
            productName = "상품명",
            itemCounts = "1",
            itemPrice = "가격: 1000pt"
        )
    )
}
