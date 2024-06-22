package com.example.hansotbob.dto

import androidx.compose.ui.graphics.painter.Painter
import com.google.firebase.Timestamp
import java.util.Date

data class Review(
    val nickname: String = "",
    val reviewContent: String = "",
    val profileImage: String = "__NULL__",
    val rating: Float = 0.0f
)

data class FirebaseReview(
    val reviewAuthorId: String = "",
    val reviewContent: String = "",
    val rating: Float = 0.0f,
    val timestamp: Long = System.currentTimeMillis()
)


