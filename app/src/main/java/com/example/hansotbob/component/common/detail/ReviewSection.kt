package com.example.hansotbob.component.common.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hansotbob.component.CardView.ReviewCard
import com.example.hansotbob.dto.Review
import com.example.hansotbob.ui.theme.ReviewInputColor
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle

@Composable
fun ReviewSection(
    reviewList: List<Review>,
    onReviewSubmit: (String, Float) -> Unit
) {
    var reviewText by remember { mutableStateOf("") }
    var rating: Float by remember { mutableStateOf(5.0f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        RatingBar(
            value = rating,
            style = RatingBarStyle.Stroke(),
            spaceBetween = 4.dp,
            modifier = Modifier.padding(start = 4.dp, bottom = 5.dp),
            onValueChange = {
                rating = it
            },
            onRatingChanged = {
                Log.d("TAG", "onRatingChanged: $it")
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(ReviewInputColor, shape = RoundedCornerShape(50))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ReviewTextField(
                value = reviewText,
                onValueChange = { reviewText = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Button(
                onClick = {
                    onReviewSubmit(reviewText, rating)
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "등록")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        ReviewList(reviews = reviewList)
    }
}


@Composable
fun ReviewList(reviews: List<Review>) {
    Column {
        reviews.forEach { review ->
            ReviewCard(
                nickname = review.nickname,
                reviewContent = review.reviewContent,
                profileImageUrl = review.profileImage,
                rating = review.rating,
                onEditClicked = { /* Handle edit click */ },
                onDeleteClicked = { /* Handle delete click */ }
            )
        }
    }
}