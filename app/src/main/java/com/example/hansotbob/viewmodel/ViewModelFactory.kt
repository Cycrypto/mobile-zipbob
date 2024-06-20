package com.example.hansotbob.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hansotbob.service.FirebaseService
import com.example.hansotbob.viewmodel.screen.detail.MealkitDetailViewModel

class ViewModelFactory(private val firebaseService: FirebaseService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealkitDetailViewModel::class.java)) {
            return MealkitDetailViewModel(firebaseService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
