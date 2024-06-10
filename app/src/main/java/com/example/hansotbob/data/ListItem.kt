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

    data class Restaurant(
        val imageResource: Int,
        val name: String,
        val details: String
    ) : ListItem()
}
