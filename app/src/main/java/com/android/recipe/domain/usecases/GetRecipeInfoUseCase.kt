package com.android.recipe.domain.usecases

import com.android.recipe.domain.RecipeRepository

class GetRecipeInfoUseCase(
    private val repository: RecipeRepository
) {
    operator fun invoke(id: Int) = repository.getRecipeInfo(id)
}