package com.example.hansotbob.viewmodel.form

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.dto.FoodShareContent
import com.example.hansotbob.service.FirebaseService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharingFoodFormViewModel(
    private val firebaseService:FirebaseService = FirebaseService()
) : ViewModel() {
    private val _title = MutableStateFlow("")
    private val _category = MutableStateFlow("")
    private val _quantity = MutableStateFlow("")
    private val _productionDate = MutableStateFlow("")
    private val _place = MutableStateFlow("")
    private val _method = MutableStateFlow("")
    private val _price = MutableStateFlow("")
    private val _description = MutableStateFlow("")
    private val _uploadStatus = MutableStateFlow("")

    val title: StateFlow<String> = _title
    val category: StateFlow<String> = _category
    val quantity: StateFlow<String> = _quantity
    val productionDate: StateFlow<String> = _productionDate
    val place: StateFlow<String> = _place
    val method: StateFlow<String> = _method
    val price: StateFlow<String> = _price
    val description: StateFlow<String> = _description
    val uploadStatus: StateFlow<String> = _uploadStatus

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setCategory(category: String) {
        _category.value = category
    }

    fun setQuantity(quantity: String) {
        _quantity.value = quantity
    }

    fun setProductionDate(date: String) {
        _productionDate.value = date
    }

    fun setPlace(place: String) {
        _place.value = place
    }

    fun setMethod(method: String) {
        _method.value = method
    }

    fun setPrice(price: String) {
        _price.value = price
    }

    fun setDescription(description: String) {
        _description.value = description
    }


    fun uploadItem(){
        viewModelScope.launch{
            val newItem = FoodShareContent(
                title = _title.value,
                category = _category.value,
                quantity = _quantity.value,
                productionDate = _productionDate.value,
                place = _place.value,
                method = _method.value,
                price = _price.value,
                description = _description.value
            )
            Log.d("SharingFoodFormViewModel", "Uploading item: $newItem")
            firebaseService.uploadMealContent(newItem)
        }
    }
}
