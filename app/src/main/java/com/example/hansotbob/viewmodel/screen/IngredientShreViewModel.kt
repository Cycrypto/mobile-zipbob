package com.example.hansotbob.viewmodel.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.dto.IngredientShareContent
import com.example.hansotbob.service.FirebaseService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IngredientShreViewModel: ViewModel() {
    private val firebaseService = FirebaseService()
    private val _items = MutableStateFlow<List<IngredientShareContent>>(emptyList())
    val items:StateFlow<List<IngredientShareContent>> = _items

    init {
        loadItems()
    }

    fun loadItems(){
        viewModelScope.launch {
            val loadItems = firebaseService.getIngredientItems()
            _items.emit(loadItems)
        }
    }
}