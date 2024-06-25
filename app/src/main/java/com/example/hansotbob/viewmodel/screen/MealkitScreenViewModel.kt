package com.example.hansotbob.viewmodel.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.R
import com.example.hansotbob.dto.MealkitsContent
import com.example.hansotbob.service.FirebaseService
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealkitScreenViewModel : ViewModel() {
    private val firebaseService = FirebaseService()
    private val _items = MutableStateFlow<List<MealkitsContent>>(emptyList())
    val items: StateFlow<List<MealkitsContent>> = _items

    init{
        loadItems()
    }

    fun loadItems() {
        viewModelScope.launch {
            try{
                val mealkits = firebaseService.getMealkitContents()
                Log.d("MealkitScreenViewModel", "${mealkits}")
                _items.value = mealkits
            }catch(e: Exception){
                Log.d("MealkitScreenViewModel", "${e}")
                _items.value = emptyList()
            }
        }
    }

    private fun MealkitDummyData(): List<MealkitsContent> {
        return listOf(
            MealkitsContent(
                itemId = "1",
                title = "밥 가져가실분1",
                category = "한식",
                quantity = "6명",
                productionDate = "2024-06-20",
                place = "산기대",
                method = "직거래",
                price = 10000,
                description = "맛있는 한식 밀키트",
            ),
            MealkitsContent(
                itemId = "2",
                title = "밥 가져가실분2",
                category = "분식",
                quantity = "5명",
                productionDate = "2024-06-21",
                place = "서울",
                method = "택배",
                price = 15000,
                description = "맛있는 분식 밀키트",
            ),
            MealkitsContent(
                itemId = "3",
                title = "밥 가져가실분3",
                category = "중식",
                quantity = "4명",
                productionDate = "2024-06-22",
                place = "부산",
                method = "직거래",
                price = 20000,
                description = "맛있는 중식 밀키트",
            ),
            MealkitsContent(
                itemId = "4",
                title = "밥 가져가실분4",
                category = "일식",
                quantity = "3명",
                productionDate = "2024-06-23",
                place = "대전",
                method = "문고리",
                price = 2500,
                description = "맛있는 일식 밀키트",
            ),
            MealkitsContent(
                itemId = "5",
                title = "밥 가져가실분5",
                category = "양식",
                quantity = "2명",
                productionDate = "2024-06-24",
                place = "광주",
                method = "택배",
                price = 30000,
                description = "맛있는 양식 밀키트"
            )
        )
    }

}