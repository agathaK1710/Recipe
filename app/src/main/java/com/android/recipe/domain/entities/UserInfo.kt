package com.android.recipe.domain.entities


data class UserInfo(
    val id: String,
    val userName: String,
    val email: String,
    var imageByteArray: ByteArray?
) {
}