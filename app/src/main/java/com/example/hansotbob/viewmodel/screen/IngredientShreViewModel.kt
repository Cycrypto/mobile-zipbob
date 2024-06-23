package com.example.hansotbob.viewmodel.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.dto.IngredientShareContent
import com.example.hansotbob.service.FirebaseService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IngredientShreViewModel: ViewModel() {
    private val firebaseService = FirebaseService()
    private val _items = MutableStateFlow<List<IngredientShareContent>>(emptyList())
    val items:StateFlow<List<IngredientShareContent>> = _items

    private val _currentPeople = MutableStateFlow<String>("0")
    val currentPeople: StateFlow<String> = _currentPeople
    init {
        loadItems()
    }

    fun loadItems(){
        viewModelScope.launch {
            val loadItems = firebaseService.getIngredientItems()
            Log.d("ingredient", "$loadItems")
            _items.emit(loadItems)
        }
    }

    fun joinItem(itemId: String){
        viewModelScope.launch {
            val currentUser = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch
            val item = firebaseService.getIngredientItemById(itemId)
            Log.d("ingredient", "sign")

            if (item != null){
                if (!firebaseService.hasUserJoined(itemId, currentUser)){
                    firebaseService.incrementPeopleCount(itemId)
                    Log.d("ingredient", "$item")
                    val updatedItem = item.copy(currentPeople = item.currentPeople + 1)
                    if (updatedItem.currentPeople.toInt() >= updatedItem.totalPeople.toInt()){
                        firebaseService.hideItem(itemId)
                    }
                    loadItems()
                }else{
                    firebaseService.hideItem(itemId)
                }
            }
        }
    }

    fun updateCurrentPeople(itemId: String){
        viewModelScope.launch{
            val item = firebaseService.getIngredientItemById(itemId)
            if (item != null){
                _currentPeople.emit(item.currentPeople)
            }
        }
    }
}