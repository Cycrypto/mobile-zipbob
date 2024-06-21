package com.example.hansotbob.dto

import com.example.hansotbob.R

data class FoodShareContent (
    val itemId: String = "",
    val authorId: String = "",
    val category: String = "",
    val description: String = "",
    val imagePainterId: Int = R.drawable.food_image,
    val method: String = "",
    val place: String = "",
    val price: String = "",
    val productionDate: String = "",
    val quantity: String = "",
    val state: Int = 0,
    val title: String = "",
    val isNew: Boolean = false,
)