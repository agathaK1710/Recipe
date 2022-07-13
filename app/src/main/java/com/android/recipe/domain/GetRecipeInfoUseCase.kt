package com.android.recipe.domain

class GetRecipeInfoUseCase(
    private val repository: RecipeRepository
) {
    operator fun invoke(id: Int) = repository.getRecipeInfo(id)
}