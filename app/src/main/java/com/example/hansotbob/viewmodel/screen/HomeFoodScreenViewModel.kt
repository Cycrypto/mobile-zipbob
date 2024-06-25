package com.example.hansotbob.viewmodel.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.dto.MealContent
import com.example.hansotbob.service.FirebaseService
import com.example.hansotbob.R
import com.example.hansotbob.dto.FoodShareContent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeFoodScreenViewModel : ViewModel() {
    private val firebaseService = FirebaseService()
    private val _items = MutableStateFlow<List<FoodShareContent>>(emptyList())
    val items: StateFlow<List<FoodShareContent>> = _items

    init {
        loadItems()
    }

    fun loadItems() {
        viewModelScope.launch {
            val loadedItems = firebaseService.getMealContents().map { item ->
                if (item.imagePainterId == 0) {
                    item.copy(imagePainterId = R.drawable.food_image)
                } else {
                    item
                }
            }
            Log.d("FirebaseService", "before emitted")
            _items.emit(loadedItems)
        }
    }
}
