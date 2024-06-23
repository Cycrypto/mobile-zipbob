package com.example.hansotbob.dto

import com.example.hansotbob.R

data class FoodShareContent (
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
    val authorId: String = ""
    // TODO: 글쓴이 view 위해서 authorId 또는 email로 변경 필요
)