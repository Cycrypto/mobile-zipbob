package com.example.hansotbob.viewmodel.user

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.example.hansotbob.R
import com.example.hansotbob.data.User
import com.example.hansotbob.service.FirebaseService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserProfileViewModel(private val firebaseService: FirebaseService): ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val storage: FirebaseStorage = FirebaseStorage.getInstance()
    private val _user = MutableStateFlow<User?>(null)
    private val _uploading = MutableStateFlow(false)

    val user: StateFlow<User?> = _user
    val uploading: StateFlow<Boolean> = _uploading

    init{
        loadUser()
    }

    private fun loadUser(){
        val currentUser = auth.currentUser
        currentUser?.let{
            val nickname = it.displayName ?: "홍길동"
            _user.value = User(
                userName = it.email ?: "example@example.com",
                nickname = nickname,
                userPoint = 0,
                imagePainterId = it.photoUrl.hashCode() ?: 0,
                imageUrl = it.photoUrl?.toString()
            )
        }
    }

    fun updateNickname(newNickname: String){
        val currentUser = auth.currentUser
        currentUser?.let{
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(newNickname)
                .build()

            it.updateProfile(profileUpdates).addOnCompleteListener{task ->
                if(task.isSuccessful){
                    _user.value = _user.value?.copy(nickname = newNickname)
                }else{
                    //에러처리
                }
            }
        }
    }

    fun updateUserPoints(newPoints: Int){
        _user.value = _user.value?.copy(userPoint = newPoints)
    }

    fun uploadUserImage(uri:Uri){
        val currentUser = auth.currentUser ?: return
        val ref = storage.reference.child("profile_mages/${currentUser.uid}")
        _uploading.value = true

        ref.putFile(uri)
            .addOnSuccessListener { taskSnapshot ->
                ref.downloadUrl.addOnSuccessListener {downloadUrl ->
                    updateUserImage(downloadUrl.toString())
                    _uploading.value = false
                }
            }
            .addOnFailureListener{
                _uploading.value = false
            }
    }
    fun updateUserImage(imageUrl: String){
        val currentUser = auth.currentUser
        currentUser?.let{
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setPhotoUri(Uri.parse(imageUrl))
                .setPhotoUri(Uri.parse(imageUrl))
                .build()

            it.updateProfile(profileUpdates).addOnCompleteListener{task ->
                if(task.isSuccessful){
                    _user.value = _user.value?.copy(imagePainterId = imageUrl.hashCode())
                }
            }
        }
    }
}