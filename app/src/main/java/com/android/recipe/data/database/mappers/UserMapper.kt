package com.android.recipe.data.database.mappers

import com.android.recipe.data.database.entities.UserEntity
import com.android.recipe.domain.entities.UserInfo
import javax.inject.Inject

class UserMapper @Inject constructor() {
    fun mapUserEntityToInfo(userEntity: UserEntity) = UserInfo(
        id = userEntity.id,
        userName = userEntity.userName,
        email = userEntity.email,
        imageByteArray = userEntity.imageByteArray
    )

    fun mapUserInfoToEntity(userInfo: UserInfo) = userInfo.imageByteArray?.let {
        UserEntity(
        id = userInfo.id,
        userName = userInfo.userName,
        email = userInfo.email,
        imageByteArray = it
    )
    }
}