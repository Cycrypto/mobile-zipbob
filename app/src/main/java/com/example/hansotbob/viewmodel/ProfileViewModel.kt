package com.example.hansotbob.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hansotbob.data.User
import com.example.hansotbob.service.FirebaseService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ProfileViewModel(private val firebaseService: FirebaseService) : ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            val currentUser = FirebaseAuth.getInstance().currentUser!!
            val userProfile = firebaseService.getUser(currentUser.uid)
            _user.value = userProfile
        }
    }
}