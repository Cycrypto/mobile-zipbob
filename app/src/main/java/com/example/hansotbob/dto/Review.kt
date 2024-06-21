package com.example.hansotbob.dto

import androidx.compose.ui.graphics.painter.Painter

data class Review(
    val nickname: String,
    val reviewContent: String,
    val profileImage: Painter,
    val rating: Float
)

fun calculateAverageRating(reviews: List<Review>): Float {
    if (reviews.isEmpty()) {
        return 5.0f
    }

    val totalRating = reviews.map { it.rating }.sum()
    return totalRating / reviews.size
}

