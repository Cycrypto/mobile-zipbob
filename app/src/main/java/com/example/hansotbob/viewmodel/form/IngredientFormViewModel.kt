package com.example.hansotbob.viewmodel.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.dto.IngredientShareContent
import com.example.hansotbob.dto.MealkitsContent
import com.example.hansotbob.service.FirebaseService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IngredientFormViewModel(
    private val firebaseService: FirebaseService = FirebaseService()
) : ViewModel() {
    private val _title = MutableStateFlow("")
    private val _category = MutableStateFlow("")
    private val _place = MutableStateFlow("")
    private val _price = MutableStateFlow("")
    private val _participant = MutableStateFlow("")
    private val _description = MutableStateFlow("")

    val title: StateFlow<String> = _title
    val category: StateFlow<String> = _category
    val place: StateFlow<String> = _place
    val price: StateFlow<String> = _price
    val participant:StateFlow<String> = _participant
    val description: StateFlow<String> = _description

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setCategory(category: String) {
        _category.value = category
    }


    fun setPlace(place: String) {
        _place.value = place
    }

    fun setPrice(price: String) {
        _price.value = price
    }

    fun setParticipant(participant: String) {
        _participant.value = participant
    }

    fun setDescription(description: String) {
        _description.value = description
    }

    fun uploadIngredient() {
        viewModelScope.launch {
            val currentUser = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch
            try {
                val ingredient = IngredientShareContent(
                    author = currentUser,
                    title = _title.value,
                    category = _category.value,
                    description = _description.value,
                    totalCost = _price.value,
                    totalPeople = _participant.value,
                    currentPeople = "0",
                    location = _place.value
                )
                firebaseService.uploadIngredientContent(ingredient)
            } catch (e: Exception) {
                //예외 처리
            }
        }
    }
}
