package com.example.hansotbob.viewmodel.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.dto.MealContent
import com.example.hansotbob.service.FirebaseService
import com.example.hansotbob.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeFoodScreenViewModel : ViewModel() {
    private val firebaseService = FirebaseService()
    private val _items = MutableStateFlow<List<MealContent>>(emptyList())
    val items: StateFlow<List<MealContent>> = _items

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
            _items.emit(loadedItems)
        }
    }

    fun addItem(item: MealContent) {
        viewModelScope.launch {
            firebaseService.uploadMealContent(item)
            loadItems() // 데이터를 업로드한 후 다시 로드합니다.
        }
    }
}
