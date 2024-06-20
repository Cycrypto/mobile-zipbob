package com.example.hansotbob.service

import android.util.Log
import com.example.hansotbob.dto.MealContent
import com.example.hansotbob.R
import com.example.hansotbob.dto.MealkitsContent
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

    suspend fun uploadMealkitContent(mealkit: MealkitsContent) {
        val key = database.child("mealkits").push().key ?: return
        val newMealkit = mealkit.copy(itemId = key)
        database.child("mealkits").child(key).setValue(newMealkit).await()
    }

    suspend fun getMealkitContents(): List<MealkitsContent> {
        Log.d("FirebaseService", "Fetching mealkits from database")
        val snapshot = database.child("mealkits").get().await()
        val items = mutableListOf<MealkitsContent>()
        Log.d("FirebaseService", "Snapshot: $snapshot")
        for (child in snapshot.children) {
            val item = child.getValue(MealkitsContent::class.java)
            if (item != null) {
                items.add(item)
            }
        }
        Log.d("FirebaseService", "Fetched items: $items")
        return items
    }

    suspend fun getMealkitById(itemId: String): MealkitsContent? {
        val snapshot = database.child("mealkits").child(itemId).get().await()
        return snapshot.getValue(MealkitsContent::class.java)
    }
}
