package com.example.hansotbob.viewmodel.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.hansotbob.R

class FoodShareDetailViewModel : ViewModel() {
    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _recruit = MutableStateFlow("")
    val recruit: StateFlow<String> = _recruit

    private val _place = MutableStateFlow("")
    val place: StateFlow<String> = _place

    private val _price = MutableStateFlow("")
    val price: StateFlow<String> = _price

    private val _images = MutableStateFlow(listOf(R.drawable.food_image, R.drawable.food_image, R.drawable.food_image))
    val images: StateFlow<List<Int>> = _images

    fun loadDetails(title: String, recruit: String, place: String, price: String) {
        viewModelScope.launch {
            _title.emit(title)
            _recruit.emit(recruit)
            _place.emit(place)
            _price.emit(price)
        }
    }
}
