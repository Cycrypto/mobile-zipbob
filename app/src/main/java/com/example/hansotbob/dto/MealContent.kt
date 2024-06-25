package com.example.hansotbob.dto

import com.example.hansotbob.R

data class MealContent(
    val author: String = "",
    val itemId: String = "",
    val state: Int = 0,
    val imagePainterId: Int = R.drawable.food_image,
    val title: String = "",
    val recruit: String = "",
    val place: String = "",
    val price: String = "",
    val isNew: Boolean = false
)
