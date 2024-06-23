package com.example.hansotbob.service

import android.util.Log
import com.example.hansotbob.dto.MealContent
import com.example.hansotbob.R
import com.example.hansotbob.data.User
import com.example.hansotbob.dto.AuthorData
import com.example.hansotbob.dto.FirebaseReview
import com.example.hansotbob.dto.FoodShareContent
import com.example.hansotbob.dto.MealkitsContent
import com.example.hansotbob.dto.Review
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

    suspend fun getUser(userName: String): User?{
        val snapshot = database.child("users").child(userName).get().await()
        return snapshot.getValue(User::class.java)
    }

    fun initializeUserData(defaultName: String = "익명의 사용자", defaultNickname: String = "배고픈 솥밥이", userPoint: Int = 0, imagePainterId: Int = 0, imageUrl: String? = null, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            onFailure("No logged in user")
            return
        }

        val newUser = User(defaultName, defaultNickname, userPoint, imagePainterId, imageUrl)
        database.child("users").child(uid).setValue(newUser)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onFailure(exception.message ?: "Failed to initialize user data")
            }
    }

    suspend fun uploadMealContent(item: FoodShareContent) {
        val key = database.child("homefood").push().key ?: return
        val newItem = item.copy(itemId = key, authorId = currentUser.uid)
        database.child("homefood").child(key).setValue(newItem).await()
    }

    suspend fun getAuthor(authorId: String): AuthorData? {
        val snapshot = database.child("users").child(authorId).get().await()
        val user = snapshot.getValue(User::class.java)
        return user?.let {
            AuthorData(
                authorId = authorId,
                nickname = it.nickname,
                profileImageUrl = it.imageUrl ?: "__NULL__"
            )
        }
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
        val newMealkit = mealkit.copy(itemId = key, authorId = currentUser.uid)
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

    suspend fun uploadReview(itemId: String, reviewContent: String, rating: Float) {
        val review = FirebaseReview(
            reviewAuthorId = currentUser.uid,
            reviewContent = reviewContent,
            rating = rating,
            timestamp = System.currentTimeMillis()
        )
        val key = database.child("reviews").child(itemId).push().key ?: return
        database.child("reviews").child(itemId).child(key).setValue(review).await()
    }

    suspend fun getReviewsForItem(itemId: String): List<Review> {
        Log.d("FirebaseService", "Fetching reviews for item $itemId")
        val snapshot = database.child("reviews").child(itemId).orderByChild("timestamp").get().await()
        val reviews = mutableListOf<Review>()
        for (child in snapshot.children) {
            val firebaseReview = child.getValue(FirebaseReview::class.java)
            if (firebaseReview != null) {
                val user = getUser(firebaseReview.reviewAuthorId)
                if (user != null) {
                    reviews.add(
                        Review(
                            nickname = user.nickname,
                            reviewContent = firebaseReview.reviewContent,
                            profileImage = user.imageUrl ?: "",
                            rating = firebaseReview.rating
                        )
                    )
                } else {
                    Log.w("FirebaseService", "User not found for userId: ${firebaseReview.reviewAuthorId}")
                }
            } else {
                Log.w("FirebaseService", "firebaseReview is null for child: ${child.key}")
            }
        }
        Log.d("FirebaseService", "Returning reviews: $reviews")
        return reviews
    }

    suspend fun updateReview(itemId: String, reviewId: String, updatedReview: FirebaseReview) {
        database.child("reviews").child(itemId).child(reviewId).setValue(updatedReview).await()
    }

    suspend fun deleteReview(itemId: String, reviewId: String) {
        database.child("reviews").child(itemId).child(reviewId).removeValue().await()
    }



}
