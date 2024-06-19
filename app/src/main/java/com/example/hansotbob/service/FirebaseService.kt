package com.example.hansotbob.service

import android.util.Log
import com.example.hansotbob.dto.ListItemDTO
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
class FirebaseService {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    suspend fun uploadListItem(item: ListItemDTO) {
        Log.d("FirebaseService", "Upload Init")
        val key = database.child("items").push().key
        if (key != null) {
            database.child("items").child(key).setValue(item).await()
        }
        Log.d("FirebaseService", "UploadSuccess")
    }

    suspend fun getListItems(): List<ListItemDTO> {
        val snapshot = database.child("items").get().await()
        val items = mutableListOf<ListItemDTO>()
        for (child in snapshot.children) {
            val item = child.getValue(ListItemDTO::class.java)
            if (item != null) {
                items.add(item)
            }
        }
        return items
    }
}