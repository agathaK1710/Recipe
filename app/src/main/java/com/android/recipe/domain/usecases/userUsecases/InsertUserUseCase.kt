package com.android.recipe.domain.usecases.userUsecases

import com.android.recipe.domain.RecipeRepository
import com.android.recipe.domain.entities.UserInfo
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val repository: RecipeRepository
){
    suspend operator fun invoke(user: UserInfo) = repository.insertUser(user)
}