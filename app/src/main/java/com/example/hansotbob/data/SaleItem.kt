package com.example.hansotbob.data

data class SaleItem(
    val imagePainterId: Int,
    val productName: String,
    val itemPrice: String,
    val detail: String,
    val sale: Boolean = false,  //판매중
    val saled: Boolean = false,  //판매완료
    val count: Int = 1  //판매할 상품 개수
)
