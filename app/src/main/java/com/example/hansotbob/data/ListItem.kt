package com.example.hansotbob.data

sealed class ListItem {
    data class MealContent(
        val imagePainterId:Int,
        val title:String,
        val recruit:String,
        val place:String,
        val price:String,
        val isNew:Boolean,
        val state:Int
    ) : ListItem()

    data class Overview(
        val name:String,
        val category: String,
        val imageRes:Int
    ) : ListItem()


    /* Mealkit card list */
    data class MealkitsOverview(
        val name: String,
        val imageRes: Int,
        val postDate: String,
        val location: String,
        val price: String,
        val status: String
    ) : ListItem()


    /* Mealkit detail content */
    data class MealkitsContent(
        val imagePainterId:Int,
        val title:String,
        val recruit:String,
        val place:String,
        val price:String,
        val state:Int
    ) : ListItem()

    /* Mealkit detail content */
//    data class MealkitsContent(
//        val currentStatus: String,
//        val imagePainterId: Int,
//        val title: String,
//        val foodType: String,
//        val category: String,
//        val quantity: String,
//        val productionDate: String,
//        val exchangePlace: String,
//        val exchangeMethod: String,
//        val price: String,
//        val separateCalculation: String,
//        val averageRating: Float
//    ) : ListItem()


}
