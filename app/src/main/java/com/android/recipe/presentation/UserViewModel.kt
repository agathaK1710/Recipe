package com.android.recipe.presentation

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

class UserViewModel @Inject constructor(
    firebaseAuth: FirebaseAuth,
    userReference: DatabaseReference,
    storageReference: StorageReference
) : ViewModel() {
    val auth = firebaseAuth
    val reference = userReference.child("users")
    val currentUser = auth.currentUser
    val storage = storageReference
    val bitmap = MutableLiveData<Bitmap>()
}