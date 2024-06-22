package com.example.hansotbob.data

data class PaymentDetail(
    val productName: String,
    val itemCounts: Int,
    val itemPrice: Int, //상품의 1개 가격
    val seller: String  //판매자명
)
