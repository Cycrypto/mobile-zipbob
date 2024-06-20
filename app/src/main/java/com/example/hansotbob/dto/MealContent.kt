package com.example.hansotbob.dto

import com.example.hansotbob.R

data class MealContent(
    val itemId: String = "",
    val imagePainterId: Int = R.drawable.food_image,
    val title: String = "",
    val recruit: String = "",
    val place: String = "",
    val price: String = "",
    val isNew: Boolean = false
)
