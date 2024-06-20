package com.example.hansotbob.service

import com.example.hansotbob.dto.MealContent
import com.example.hansotbob.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseService {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    suspend fun uploadMealContent(item: MealContent) {
        val key = database.child("meal_contents").push().key ?: return
        val newItem = item.copy(itemId = key)
        database.child("meal_contents").child(key).setValue(newItem).await()
    }

    suspend fun getMealContents(): List<MealContent> {
        val snapshot = database.child("meal_contents").get().await()
        val items = mutableListOf<MealContent>()
        for (child in snapshot.children) {
            var item = child.getValue(MealContent::class.java)
            if (item != null) {
                if (item.imagePainterId == 0) {
                    item = item.copy(imagePainterId = R.drawable.food_image)
                }
                items.add(item)
            }
        }
        return items
    }
}
