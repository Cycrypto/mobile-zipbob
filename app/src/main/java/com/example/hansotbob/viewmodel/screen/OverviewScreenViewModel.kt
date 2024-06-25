package com.example.hansotbob.viewmodel.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.R
import com.example.hansotbob.dto.Overview

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OverviewScreenViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<Overview>>(emptyList())
    val items: StateFlow<List<Overview>> = _items

    fun loadItems() {
        viewModelScope.launch {
            // 예제 데이터를 로드합니다. 실제로는 데이터베이스 또는 네트워크에서 로드합니다.
            _items.emit(overviewDummyData())
        }
    }

    private fun overviewDummyData(): List<Overview> {
        return listOf(
            Overview("밥 가져가실분1", "한식", R.drawable.community_image),
            Overview("밥 가져가실분12", "한식", R.drawable.community_image),
            Overview("밥 가져가실분13", "한식", R.drawable.community_image),
            Overview("밥 가져가실분14", "한식", R.drawable.community_image),
            Overview("밥 가져가실분15", "한식", R.drawable.community_image),
            Overview("밥 가져가실분16", "한식", R.drawable.community_image)
        )
    }
}