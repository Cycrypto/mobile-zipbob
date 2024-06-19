package com.example.hansotbob.viewmodel.form

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.dto.ListItemDTO
import com.example.hansotbob.service.FirebaseService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharingFoodFormViewModel : ViewModel() {
    private val firebaseService = FirebaseService()

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _foodType = MutableStateFlow("")
    val foodType: StateFlow<String> = _foodType

    private val _category = MutableStateFlow("")
    val category: StateFlow<String> = _category

    private val _quantity = MutableStateFlow("")
    val quantity: StateFlow<String> = _quantity

    private val _manufactureDate = MutableStateFlow("")
    val manufactureDate: StateFlow<String> = _manufactureDate

    private val _place = MutableStateFlow("")
    val place: StateFlow<String> = _place

    private val _tradeMethod = MutableStateFlow("")
    val tradeMethod: StateFlow<String> = _tradeMethod

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    fun onTitleChange(newTitle: String) {
        _title.value = newTitle
    }

    fun onFoodTypeChange(newFoodType: String) {
        _foodType.value = newFoodType
    }

    fun onCategoryChange(newCategory: String) {
        _category.value = newCategory
    }

    fun onQuantityChange(newQuantity: String) {
        _quantity.value = newQuantity
    }

    fun onManufactureDateChange(newManufactureDate: String) {
        _manufactureDate.value = newManufactureDate
    }

    fun onPlaceChange(newPlace: String) {
        _place.value = newPlace
    }

    fun onTradeMethodChange(newTradeMethod: String) {
        _tradeMethod.value = newTradeMethod
    }

    fun onDescriptionChange(newDescription: String) {
        _description.value = newDescription
    }

    fun uploadItem(){
        viewModelScope.launch{
            val newItem = ListItemDTO.MealContent(
                imagePainterId = 0,
                title = _title.value,
                recruit = _foodType.value,
                place = _place.value,
                price = _quantity.value,
                isNew = true
            )
            Log.d("SharingFoodFormViewModel", "Uploading item: $newItem")
            firebaseService.uploadListItem(newItem)
        }
    }
}
