package com.example.hansotbob.dto

data class Profile(
    val name: String,
    val followers: Int,
    val following: Int,
    val profileImageUrl: String
)