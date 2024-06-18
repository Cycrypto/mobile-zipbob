package com.example.hansotbob.data

import androidx.compose.ui.graphics.painter.Painter

data class Review(
    val nickname: String,
    val reviewContent: String,
    val profileImage: Painter
)