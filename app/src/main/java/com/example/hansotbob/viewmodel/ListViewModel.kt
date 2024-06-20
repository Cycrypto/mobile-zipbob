package com.example.hansotbob.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.dto.MealContent
import com.example.hansotbob.dto.Overview
import com.example.hansotbob.dto.MealkitsContent
import com.example.hansotbob.service.FirebaseService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    private val firebaseService = FirebaseService()
    private val _mealContents = MutableStateFlow<List<MealContent>>(emptyList())
    val mealContents: StateFlow<List<MealContent>> = _mealContents

    private val _overviews = MutableStateFlow<List<Overview>>(emptyList())
    val overviews: StateFlow<List<Overview>> = _overviews

    private val _mealkitsContents = MutableStateFlow<List<MealkitsContent>>(emptyList())
    val mealkitsContents: StateFlow<List<MealkitsContent>> = _mealkitsContents

    init {
        loadMealContents()
//        loadOverviews()
//        loadMealkitsContents()
    }

    private fun loadMealContents() {
        viewModelScope.launch {
            val loadedItems = firebaseService.getMealContents()
            _mealContents.value = loadedItems
        }
    }

//    private fun loadOverviews() {
//        viewModelScope.launch {
//            val loadedItems = firebaseService.getOverviews()
//            _overviews.value = loadedItems
//        }
//    }
//
//    private fun loadMealkitsContents() {
//        viewModelScope.launch {
//            val loadedItems = firebaseService.getMealkitsContents()
//            _mealkitsContents.value = loadedItems
//        }
//    }

    fun addMealContent(item: MealContent) {
        viewModelScope.launch {
            firebaseService.uploadMealContent(item)
            loadMealContents()
        }
    }

//    fun addOverview(item: Overview) {
//        viewModelScope.launch {
//            firebaseService.uploadOverview(item)
//            loadOverviews()
//        }
//    }
//
//    fun addMealkitsContent(item: MealkitsContent) {
//        viewModelScope.launch {
//            firebaseService.uploadMealkitsContent(item)
//            loadMealkitsContents()
//        }
//    }
}
