package com.example.hansotbob.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.dto.ListItemDTO
import com.example.hansotbob.service.FirebaseService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    private val firebaseService = FirebaseService()
    private val _items = MutableStateFlow<List<ListItemDTO>>(emptyList())
    val items: StateFlow<List<ListItemDTO>> = _items

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            val loadedItems = firebaseService.getListItems()
            _items.value = loadedItems
        }
    }

    fun addItem(item: ListItemDTO) {
        viewModelScope.launch {
            firebaseService.uploadListItem(item)
            loadItems()
        }
    }
}