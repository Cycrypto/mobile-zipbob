package com.example.hansotbob.viewmodel.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.R
import com.example.hansotbob.dto.ListItemDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealkitScreenViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<ListItemDTO.MealkitsContent>>(emptyList())
    val items: StateFlow<List<ListItemDTO.MealkitsContent>> = _items

    fun loadItems() {
        viewModelScope.launch {
            _items.emit(MealkitDummyData())
        }
    }

    private fun MealkitDummyData(): List<ListItemDTO.MealkitsContent> {
        return listOf(
            ListItemDTO.MealkitsContent(R.drawable.community_image, "밥 가져가실분1", "6명", "산기대", "10000"),
            ListItemDTO.MealkitsContent(R.drawable.community_image, "밥 가져가실분1", "6명", "산기대", "10000"),
            ListItemDTO.MealkitsContent(R.drawable.community_image, "밥 가져가실분1", "6명", "산기대", "10000"),
            ListItemDTO.MealkitsContent(R.drawable.community_image, "밥 가져가실분1", "6명", "산기대", "10000"),
            ListItemDTO.MealkitsContent(R.drawable.community_image, "밥 가져가실분1", "6명", "산기대", "10000"),
        )
    }
}