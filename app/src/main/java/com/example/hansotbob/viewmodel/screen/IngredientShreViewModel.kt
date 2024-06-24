package com.example.hansotbob.viewmodel.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.dto.IngredientShareContent
import com.example.hansotbob.service.FirebaseService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class IngredientShreViewModel: ViewModel() {
    private val firebaseService = FirebaseService()
    private val _items = MutableStateFlow<List<IngredientShareContent>>(emptyList())
    val items:StateFlow<List<IngredientShareContent>> = _items

    private val _currentPeople = MutableStateFlow(0)
    val currentPeople: StateFlow<Int> = _currentPeople

    private val _totalPeople = MutableStateFlow(0)
    val totalPeople: StateFlow<Int> = _totalPeople

    private val _isAuthor = MutableStateFlow(false)
    val isAuthor: StateFlow<Boolean> = _isAuthor

    private val _hasJoined = MutableStateFlow(false)
    val hasJoined: StateFlow<Boolean> = _hasJoined

    private val _joinedParties = MutableStateFlow<List<String>>(emptyList())
    val joinedParties:StateFlow<List<String>> = _joinedParties

    private val _partyState = MutableStateFlow<Map<String, PartyState>>(emptyMap())
    val partyState: StateFlow<Map<String, PartyState>> = _partyState
    data class PartyState(
        val currentPeople: Int,
        val isAuthor: Boolean,
        val hasJoined: Boolean,
        val totalPeople: Int
    )

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

    fun joinItem(itemId: String) {
        viewModelScope.launch {
            val currentUser = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch
            val item = firebaseService.getIngredientItemById(itemId)
            val state = _partyState.value[itemId]
            if (item != null && state != null && !state.hasJoined && !state.isAuthor) {
                firebaseService.incrementPeopleCount(itemId)
                firebaseService.addParticipant(itemId, currentUser)
                val updatedItem = item.copy(currentPeople = (item.currentPeople.toInt() + 1).toString())
                val newState = state.copy(
                    currentPeople = updatedItem.currentPeople.toInt(),
                    hasJoined = true
                )
                if (newState.currentPeople >= newState.totalPeople) {
                    firebaseService.hideItem(itemId)
                }
                _partyState.value = _partyState.value.toMutableMap().apply {
                    put(itemId, newState)
                }
            }
        }
    }

    fun updatePartyState(itemId: String) {
        viewModelScope.launch {
            val item = firebaseService.getIngredientItemById(itemId)
            val currentUser = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch
            if (item != null) {
                val newState = PartyState(
                    currentPeople = item.currentPeople.toInt(),
                    isAuthor = item.author == currentUser,
                    hasJoined = firebaseService.hasUserJoined(itemId, currentUser),
                    totalPeople = item.totalPeople.toInt()
                )
                _partyState.value = _partyState.value.toMutableMap().apply {
                    put(itemId, newState)
                }
            }
        }
    }

    fun updateCurrentPeople(itemId: String){
        viewModelScope.launch{
            val item = firebaseService.getIngredientItemById(itemId)
            val currentUser = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch
            if (item != null){
                _currentPeople.emit(item.currentPeople.toInt())
                _totalPeople.emit(item.totalPeople.toInt())
                _isAuthor.emit(item.author == currentUser)
                _hasJoined.emit(firebaseService.hasUserJoined(itemId, currentUser))
                Log.d("ingredient", "isAuthor: ${item.author}, ${currentUser}, hasJoined: ${firebaseService.hasUserJoined(itemId, currentUser)}")
            }
        }
    }
}