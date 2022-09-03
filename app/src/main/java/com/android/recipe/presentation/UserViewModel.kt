package com.android.recipe.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.recipe.domain.entities.UserInfo
import com.android.recipe.domain.usecases.userUsecases.DeleteUserUseCase
import com.android.recipe.domain.usecases.userUsecases.EditUserUseCase
import com.android.recipe.domain.usecases.userUsecases.GetUserUseCase
import com.android.recipe.domain.usecases.userUsecases.InsertUserUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    firebaseAuth: FirebaseAuth,
    userReference: DatabaseReference,
    storageReference: StorageReference,
    private val getUserUseCase: GetUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val insertUserUseCase: InsertUserUseCase,
    private val editUserUseCase: EditUserUseCase
) : ViewModel() {
    val auth = firebaseAuth
    val reference = userReference.child("users")
    val currentUser = auth.currentUser
    val storage = storageReference

    fun getUser(id: String) = getUserUseCase(id)
    private suspend fun insertUser(user: UserInfo) = insertUserUseCase(user)
    suspend fun deleteUser(user: UserInfo) = deleteUserUseCase(user)
    suspend fun updateUser(user: UserInfo) = editUserUseCase(user)


    fun addUser(){
        currentUser?.uid?.let { id ->
            reference.child(id).get()
                .addOnSuccessListener { dataSnapshot ->
                    val user = dataSnapshot.getValue(User::class.java)
                    storage.child("images/${id}")
                        .getBytes(Long.MAX_VALUE)
                        .addOnSuccessListener { byteArray ->
                            if(user != null) {
                                val userInfo = UserInfo(id, user.username, user.email, byteArray)
                                Log.d("userViewModel", userInfo.toString())
                                viewModelScope.launch(Dispatchers.IO) {
                                    insertUser(userInfo)
                                }
                            }
                        }
                }
        }
    }
}