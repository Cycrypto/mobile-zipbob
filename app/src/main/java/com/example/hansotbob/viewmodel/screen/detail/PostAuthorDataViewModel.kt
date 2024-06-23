package com.example.hansotbob.viewmodel.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.dto.AuthorData
import com.example.hansotbob.dto.FoodShareContent
import com.example.hansotbob.service.FirebaseService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostAuthorDataViewModel(private val firebaseService: FirebaseService) : ViewModel() {
    private val _author = MutableStateFlow<AuthorData?>(null)
    val author: StateFlow<AuthorData?> = _author

    fun loadItem(authorId: String) {
        viewModelScope.launch {
            val authorItem = firebaseService.getAuthor(authorId)
            _author.value = authorItem ?: AuthorData(
                authorId = authorId,
                nickname = "Temporary Nickname",
                profileImageUrl = "__NULL__"
            )
        }
    }
}

