package com.example.hansotbob.viewmodel.screen.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.dto.FirebaseReview
import com.example.hansotbob.dto.Review
import com.example.hansotbob.service.FirebaseService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow

class ReviewViewModel(private val firebaseService: FirebaseService) : ViewModel() {
    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews: StateFlow<List<Review>> = _reviews

    val auth = FirebaseAuth.getInstance()
    private val currentUser = auth.currentUser


    fun loadReviews(itemId: String) {
        viewModelScope.launch {
            val reviews = firebaseService.getReviewsForItem(itemId)
            _reviews.value = reviews
            Log.d("ReviewViewModel", "Loaded reviews: $reviews")
        }
    }

    fun uploadReview(itemId: String, reviewContent: String, rating: Float) {
        viewModelScope.launch {
            firebaseService.uploadReview(itemId, reviewContent, rating)
            loadReviews(itemId)
        }
    }


    fun updateReview(itemId: String, reviewId: String, updatedReview: FirebaseReview) {
        viewModelScope.launch {
            firebaseService.updateReview(itemId, reviewId, updatedReview)
            loadReviews(itemId)
        }
    }

    fun deleteReview(itemId: String, reviewId: String) {
        viewModelScope.launch {
            firebaseService.deleteReview(itemId, reviewId)
            loadReviews(itemId)
        }
    }

    fun loadReviewsForCurrentUser() {
        viewModelScope.launch {
            try {
                val reviews = firebaseService.getReviewsForAuthor(currentUser!!.uid)
                _reviews.value = reviews
                Log.d("ReviewViewModel", "Loaded reviews: $reviews")
            } catch (e: Exception) {
                Log.e("ReviewViewModel", "Error loading reviews", e)
            }
        }
    }
}
