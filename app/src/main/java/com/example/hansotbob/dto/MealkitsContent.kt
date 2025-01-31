package com.example.hansotbob.dto

import com.example.hansotbob.R

data class MealkitsContent(
    val category: String ="",
    val description: String = "",
    val imagePainterId: Int = R.drawable.mealkits_image,
    val itemId: String = "",
    val method: String = "",
    val place: String = "",
    val price: Int = 0,
    val productionDate: String = "",
    val quantity: String = "",
    val state: Int = 0,
    val title: String = "",
    val authorId: String = "",
    val reviews: List<Review> = emptyList()
)
