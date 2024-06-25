package com.example.hansotbob.viewmodel.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.dto.MealkitsContent
import com.example.hansotbob.dto.Review
import com.example.hansotbob.service.FirebaseService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealkitDetailViewModel (private val firebaseService: FirebaseService) : ViewModel() {
    private val _mealkit = MutableStateFlow<MealkitsContent?>(null)
    val mealkit: StateFlow<MealkitsContent?> = _mealkit


    fun loadMealkit(itemId: String){
        viewModelScope.launch {
            val mealkitItem = firebaseService.getMealkitById(itemId)
            _mealkit.value = mealkitItem
        }
    }
}