package com.example.hansotbob.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hansotbob.service.FirebaseService
import com.example.hansotbob.viewmodel.screen.detail.FoodShareDetailViewModel
import com.example.hansotbob.viewmodel.screen.detail.MealkitDetailViewModel
import com.example.hansotbob.viewmodel.screen.detail.ReviewViewModel
import com.example.hansotbob.viewmodel.user.UserProfileViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val firebaseService: FirebaseService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MealkitDetailViewModel::class.java) -> MealkitDetailViewModel(firebaseService) as T
            modelClass.isAssignableFrom(ReviewViewModel::class.java) -> ReviewViewModel(firebaseService) as T
            modelClass.isAssignableFrom(UserProfileViewModel::class.java) -> UserProfileViewModel(firebaseService) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
