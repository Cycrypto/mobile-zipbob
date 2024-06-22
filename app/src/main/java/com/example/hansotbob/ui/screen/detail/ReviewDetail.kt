package com.example.hansotbob.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hansotbob.R
import com.example.hansotbob.data.ReviewDetail

@Composable
fun RatingBar(maxRating: Int = 5, initialRating: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        for (i in 1..maxRating) {
            val starImage = when {
                initialRating >= i -> R.drawable.ic_star_filled
                initialRating >= i - 0.5 -> R.drawable.ic_star_half
                else -> R.drawable.ic_star_outline
            }
            Image(
                painter = painterResource(starImage),
                contentDescription = "Star",
                modifier = Modifier
                    .size(30.dp)
                    .padding(4.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "$initialRating / 5.0")
    }
}


@Composable
fun ReviewDetail(
    reviewDetail: ReviewDetail,
    border: Dp = 1.dp,
    shape: Shape = RoundedCornerShape(8.dp),
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White)
            .border(border, Color.LightGray, shape)
            .padding(16.dp)
    ) {
        // Product Image
        Image(
            painter = painterResource(id = reviewDetail.imagePainterId),
            contentDescription = "Product Image",
            modifier = Modifier
                .size(100.dp)
                .clip(shape) // Rounded corners for the image
        )

        // Spacer between image and details
        Spacer(modifier = Modifier.width(16.dp))

        // Product Details
        Column(
            modifier = Modifier.weight(1f)
        ) {
            // product, user Name
            Text(
                text = reviewDetail.productName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = " ${reviewDetail.userName} / ${reviewDetail.getFormattedCreatedAt()} ",
                fontSize = 12.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Rating Bar
            RatingBar(
                maxRating = 5,
                initialRating = reviewDetail.reviewRating
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Review Detail
            Text(
                text = reviewDetail.detail,
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
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
            userName = "감자",
            reviewRating = 3.5f,
            detail = "너무너무 맛있는 레시피였어요 자주 만들어 먹겠습니다!",
            productName = "스파게티 나눔",
        )
    )
}
