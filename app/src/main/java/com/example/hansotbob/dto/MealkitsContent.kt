package com.example.hansotbob.dto

import com.example.hansotbob.R

data class MealkitsContent(
    val category: String ="",
    val description: String = "",
    val imagePainterId: Int = R.drawable.mealkits_image,
    val itemId: String = "",
    val method: String = "",
    val place: String = "",
    val price: String = "",
    val productionDate: String = "",
    val quantity: String = "",
    val state: Int = 0,
    val title: String = "",
    val author: String = ""
    // TODO: 글쓴이 view 위해서 authorId로 변경 필요
)
