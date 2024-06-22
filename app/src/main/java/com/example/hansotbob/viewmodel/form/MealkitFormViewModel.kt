package com.example.hansotbob.viewmodel.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.dto.MealkitsContent
import com.example.hansotbob.service.FirebaseService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealkitFormViewModel(
    private val firebaseService: FirebaseService = FirebaseService()
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

    var errorMessage by mutableStateOf("")
    val title: StateFlow<String> = _title
    val category: StateFlow<String> = _category
    val quantity: StateFlow<String> = _quantity
    val productionDate: StateFlow<String> = _productionDate
    val place: StateFlow<String> = _place
    val method: StateFlow<String> = _method
    val price: StateFlow<String> = _price
    val description: StateFlow<String> = _description
    val uploadStatus: StateFlow<String> = _uploadStatus
    var isLoading by mutableStateOf(false)

    fun register(): Boolean {
        return if (_title.value.isEmpty() || _category.value.isEmpty() || _quantity.value.isEmpty()
            || _productionDate.value.isEmpty() || _place.value.isEmpty() || _method.value.isEmpty()
            || _price.value.isEmpty() || _description.value.isEmpty()) {
            errorMessage = "모든 빈칸을 입력하세요"
            false
        } else {
            true
        }
    }

    fun checkPriceIsValid(): Boolean {
        return try {
            _price.value.toDouble()
            true
        } catch (e: NumberFormatException) {
            errorMessage = "가격은 숫자여야 합니다"
            false
        }
    }




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

    fun uploadMealkit() {
        viewModelScope.launch {
            try {
                val mealkit = MealkitsContent(
                    title = _title.value,
                    category = _category.value,
                    quantity = _quantity.value,
                    productionDate = _productionDate.value,
                    place = _place.value,
                    method = _method.value,
                    price = _price.value,
                    description = _description.value
                )
                firebaseService.uploadMealkitContent(mealkit)
                _uploadStatus.value = "Upload Successful"
            } catch (e: Exception) {
                _uploadStatus.value = "Upload Failed: ${e.message}"
            }
        }
    }
}
