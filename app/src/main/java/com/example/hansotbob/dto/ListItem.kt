package com.example.hansotbob.dto

sealed class ListItemDTO {
    data class MealContent(
        val imagePainterId:Int,
        val title:String,
        val recruit:String,
        val place:String,
        val price:String,
        val isNew:Boolean
    ) : ListItemDTO()

    data class Overview(
        val name:String,
        val category: String,
        val imageRes:Int
    ) : ListItemDTO()

    data class MealkitsContent(
        val imagePainterId:Int,
        val title:String,
        val recruit:String,
        val place:String,
        val price:String
    ) : ListItemDTO()

}
