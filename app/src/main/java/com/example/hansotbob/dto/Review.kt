package com.example.hansotbob.dto

import androidx.compose.ui.graphics.painter.Painter

data class Review(
    val nickname: String,
    val reviewContent: String,
    val profileImage: Painter
)