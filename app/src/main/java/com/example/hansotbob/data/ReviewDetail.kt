package com.example.hansotbob.data

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ReviewDetail(
    val imagePainterId: Int,
    val userName: String,
    val reviewRating: Float,
    val detail: String,
    val productName: String,
    val createdAt: Date = Date()
) {
    fun getFormattedCreatedAt(): String {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        return dateFormat.format(createdAt)
    }
}
