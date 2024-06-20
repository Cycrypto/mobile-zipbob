package com.example.hansotbob.viewmodel.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.hansotbob.R
import com.example.hansotbob.dto.FoodShareContent
import com.example.hansotbob.dto.MealContent
import com.example.hansotbob.service.FirebaseService

class FoodShareDetailViewModel : ViewModel() {
    private val firebaseService = FirebaseService()
    private val _item = MutableStateFlow<MealContent?>(null)
    val item: StateFlow<MealContent?> = _item

    fun loadItem(itemId: String) {
        viewModelScope.launch {
            val items = firebaseService.getMealContents()
            _item.value = items.find { it.itemId == itemId }
        }
    }
}