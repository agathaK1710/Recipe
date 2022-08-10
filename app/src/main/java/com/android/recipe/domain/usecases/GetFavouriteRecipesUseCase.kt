package com.android.recipe.domain.usecases

import com.android.recipe.domain.RecipeRepository

class GetFavouriteRecipesUseCase(
    private val repository: RecipeRepository
) {
    operator fun invoke() = repository.getFavouriteRecipesList()
}