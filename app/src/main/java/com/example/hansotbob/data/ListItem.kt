package com.example.hansotbob.data

sealed class ListItem {
    data class MealContent(
        val imagePainterId:Int,
        val title:String,
        val recruit:String,
        val place:String,
        val price:String,
        val isNew:Boolean
    ) : ListItem()

    data class Overview(
        val name:String,
        val category: String,
        val imageRes:Int
    ) : ListItem()

    data class MealkitsContent(
        val imagePainterId:Int,
        val title:String,
        val recruit:String,
        val place:String,
        val price:String
    ) : ListItem()

    data class ItemDetail(
        val imagePainterId: Int,
        val productName: String,
        val itemPrice: String,
        val detail: String
    ) : ListItem()

    data class ReviewDetail(
        val imagePainterId: Int,
        val userName: String,
        val reviewRating: String,
        val detail: String
    ) : ListItem()

    data class PaymentDetail(
        val productName: String,
        val itemCounts: String,
        val itemPrice: String
    ) : ListItem()
}
