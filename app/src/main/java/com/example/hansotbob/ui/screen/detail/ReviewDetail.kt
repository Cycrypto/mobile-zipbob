package com.example.hansotbob.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.hansotbob.R
import com.example.hansotbob.data.ListItem.ReviewDetail

@Composable
fun ReviewDetail(
    reviewDetail: ReviewDetail,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White)
    ) {
        // Product Image
        Image(
            painter = painterResource(id = reviewDetail.imagePainterId),
            contentDescription = "Product Image",
            modifier = Modifier
                .size(100.dp)
        )

        // Product Details
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        ) {
            Text(
                text = reviewDetail.userName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = reviewDetail.reviewRating,
                fontSize = 14.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = reviewDetail.detail,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewDetailPreview() {
    ReviewDetail(
        reviewDetail = ReviewDetail(
            imagePainterId = R.drawable.ic_mypage,
            userName = "닉네임",
            reviewRating = "평점: 4.0/5.0점 ",
            detail = "리뷰내역 예시입니다."
        )
    )
}
