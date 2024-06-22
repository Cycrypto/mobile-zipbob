package com.example.hansotbob.service

import android.util.Log
import com.example.hansotbob.dto.MealContent
import com.example.hansotbob.R
import com.example.hansotbob.data.User
import com.example.hansotbob.dto.FoodShareContent
import com.example.hansotbob.dto.MealkitsContent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import java.lang.IllegalStateException

class FirebaseService {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val currentUser = auth.currentUser!!
    private val nickname = currentUser.displayName ?: "배고픈 무지"

    suspend fun uploadUser(user: User){
        val key = user.userName
        database.child("users").child(key).setValue(user).await()
    }

    suspend fun getUser(userName: String): User?{
        val snapshot = database.child("users").child(userName).get().await()
        return snapshot.getValue(User::class.java)
    }

    suspend fun uploadMealContent(item: FoodShareContent) {
        val key = database.child("homefood").push().key ?: return
        val newItem = item.copy(itemId = key, authorId = nickname)
        database.child("homefood").child(key).setValue(newItem).await()
    }

    suspend fun getMealContents(): List<FoodShareContent> {
        Log.d("FirebaseService", "Fetching mealkits from database")
        val snapshot = database.child("homefood").get().await()
        Log.d("FirebaseService", "Snapshot: $snapshot")
        val items = mutableListOf<FoodShareContent>()
        for (child in snapshot.children) {
            var item = child.getValue(FoodShareContent::class.java)
            if (item != null) {
                if (item.imagePainterId == 0) {
                    item = item.copy(imagePainterId = R.drawable.food_image)
                }
                items.add(item)
            }
        }
        Log.d("FirebaseService", "Fetched items: $items")
        return items
    }

    suspend fun uploadMealkitContent(mealkit: MealkitsContent) {
        val key = database.child("mealkits").push().key ?: return
        val newMealkit = mealkit.copy(itemId = key, author = nickname)
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

    suspend fun getMealkitsByUser(userId: String): List<MealkitsContent>{
        val snapshot = database.child("user_mealkits")
            .child(userId).get().await()
        val itemIds = snapshot.children.map{it.key}
        val items = mutableListOf<MealkitsContent>()
        for (itemId in itemIds){
            val itemSnapshot = database.child("mealkits").child(itemId!!).get().await()
            val item = itemSnapshot.getValue(MealkitsContent::class.java)
            if(item != null){
                items.add(item)
            }
        }
        return items
    }
}
