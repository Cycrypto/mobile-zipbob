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


}
