package com.example.hansotbob.viewmodel.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.R
import com.example.hansotbob.dto.ListItemDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeFoodScreenViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<ListItemDTO.MealContent>>(emptyList())
    val items: StateFlow<List<ListItemDTO.MealContent>> = _items

    fun loadItems() {
        viewModelScope.launch {
            _items.emit(HomeFoodDummyData())
        }
    }

    private fun HomeFoodDummyData(): List<ListItemDTO.MealContent> {
        return listOf(
            ListItemDTO.MealContent(R.drawable.food_image,"집밥 가져가실분", "1명", "우리 집", "1234원", true),
            ListItemDTO.MealContent(R.drawable.food_image,"집밥 가져가실분", "1명", "우리 집", "1234원", false),
            ListItemDTO.MealContent(R.drawable.food_image,"집밥 가져가실분", "1명", "우리 집", "1234원", false),
            ListItemDTO.MealContent(R.drawable.food_image,"집밥 가져가실분", "1명", "우리 집", "1234원", true)
        )
    }
}

