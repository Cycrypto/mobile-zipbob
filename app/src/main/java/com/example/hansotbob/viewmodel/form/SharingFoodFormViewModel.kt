package com.example.hansotbob.viewmodel.form

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.R
import com.example.hansotbob.dto.MealContent
import com.example.hansotbob.service.FirebaseService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharingFoodFormViewModel : ViewModel() {
    private val firebaseService = FirebaseService()
    private val _title = MutableStateFlow("")
    private val _foodType = MutableStateFlow("")
    private val _category = MutableStateFlow("")
    private val _quantity = MutableStateFlow("")
    private val _manufactureDate = MutableStateFlow("")
    private val _place = MutableStateFlow("")
    private val _tradeMethod = MutableStateFlow("")
    private val _description = MutableStateFlow("")

    val title: StateFlow<String> = _title
    val foodType: StateFlow<String> = _foodType
    val category: StateFlow<String> = _category
    val quantity: StateFlow<String> = _quantity
    val manufactureDate: StateFlow<String> = _manufactureDate
    val place: StateFlow<String> = _place
    val tradeMethod: StateFlow<String> = _tradeMethod
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
            val newItem = MealContent(
                imagePainterId = R.drawable.food_image, // 실제 이미지 리소스
                title = _title.value,
                recruit = _foodType.value,
                place = _place.value,
                price = _quantity.value,
                isNew = true
            )
            Log.d("SharingFoodFormViewModel", "Uploading item: $newItem")
            firebaseService.uploadMealContent(newItem)
        }
    }
}
