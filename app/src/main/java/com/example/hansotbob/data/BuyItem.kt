package com.example.hansotbob.data

data class BuyItem(
    val imagePainterId: Int,
    val productName: String,
    val itemPrice: String,
    val detail: String,
    val inCart: Boolean = false,      //장바구니
    val purchased: Boolean = false,    //구매완료
    val count: Int = 1  //선택한 상품 개수 선택안하면 default 1
)
