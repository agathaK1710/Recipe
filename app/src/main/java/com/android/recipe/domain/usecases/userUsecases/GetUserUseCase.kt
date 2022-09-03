package com.android.recipe.domain.usecases.userUsecases

import com.android.recipe.domain.RecipeRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke(id: String) = repository.getUser(id)
}