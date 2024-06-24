package com.example.hansotbob.dto

data class IngredientShareContent(
    val author: String = "",
    val category: String = "",
    var currentPeople: String = "0",
    val description: String = "",
    val imageResource: String = "",
    val itemId: String = "",
    val location: String = "",
    val participants: Map<String, Boolean> = emptyMap(),
    val title: String = "",
    val totalCost: String = "",
    val totalPeople: String = "",
    val hidden: Boolean = false
)