package com.example.hansotbob.viewmodel.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.dto.CardItem
import com.example.hansotbob.dto.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class OverviewViewModel : ViewModel() {
    private val _profile = MutableStateFlow<Profile?>(null)
    val profile: StateFlow<Profile?> = _profile

    private val _cards = MutableStateFlow<List<CardItem>>(emptyList())
    val cards: StateFlow<List<CardItem>> = _cards

    init {
        loadProfile()
        loadCards()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            // Load profile data (this is just a placeholder)
            _profile.value = Profile(
                name = "Neha",
                followers = 487,
                following = 224,
                profileImageUrl = "https://oasisproduct.cdn.ntruss.com/30322/thumb/999"
            )
        }
    }

    private fun loadCards() {
        viewModelScope.launch {
            // Load card data (this is just a placeholder)
            _cards.value = listOf(
                CardItem(
                    title = "불고기 무료나눔",
                    imageUrl = "https://oasisproduct.cdn.ntruss.com/30322/thumb/999",
                    timeAgo = "12 분 전"
                ),
                CardItem(
                    title = "블랙라벨 스테이크",
                    imageUrl = "https://pds.joongang.co.kr/news/component/htmlphoto_mmdata/202107/21/4fb3a817-899e-408f-8768-66ad7195d61b.jpg",
                    timeAgo = "1시간 전"
                )
            )
        }
    }
}