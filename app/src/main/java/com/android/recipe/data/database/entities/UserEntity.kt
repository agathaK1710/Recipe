package com.android.recipe.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val userName: String,
    val email: String,
    val imageByteArray: ByteArray?
)